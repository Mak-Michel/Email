package com.example.Email_Back.Model.Caches;

import com.example.Email_Back.Model.Caches.Threads.EmptyBuffer;
import com.example.Email_Back.Model.Caches.Threads.FreeCacheSpace;
import com.example.Email_Back.Model.User.User;
import com.example.Email_Back.Model.Gateways.UserGateway;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

@Component
public class UserCache {

    private EmptyBuffer emptyBuffer = new EmptyBuffer();
    private FreeCacheSpace freeCacheSpace = new FreeCacheSpace();
    private HashMap<String, CacheBlock> cache = new HashMap<>();
    private Queue<String> buffer = new LinkedList<>();

    private UserGateway database = new UserGateway();

    private final int maxBufferSize = 5;
    private final int maxSize = 10;

    public void loadFromDB(String email){
        if(!UserGateway.exists(email))
            return;
        this.cache.put(email, new CacheBlock(database.load(email), System.currentTimeMillis()));
        System.out.println("Cache size: " + this.cache.size());
        System.out.println("Buffer size: " + this.buffer.size());
    }

    public User retrieve(String email) {
        if(!this.cache.containsKey(email))
            this.loadFromDB(email);
        try {
            this.cache.get(email).counter = System.currentTimeMillis();
        }catch (NullPointerException e){return null;}
        System.out.println("Cache size: " + this.cache.size());
        System.out.println("Buffer size: " + this.buffer.size());
        return (User) this.cache.get(email).cachedObject;
    }

    public void update(User updatedUser){
        //check if element in cache
        if(!cache.containsKey(updatedUser.getId()))
            this.loadFromDB(updatedUser.getId()); //if miss load it from memory
        //load the email into cache
        this.cache.put(updatedUser.getId(), new CacheBlock(updatedUser, System.currentTimeMillis(), true));
        //add id to buffer to be updated in DB
        if(!buffer.contains(updatedUser.getId()))
            this.buffer.add(updatedUser.getId());
        System.out.println("Cache size: " + this.cache.size());
        System.out.println("Buffer size: " + this.buffer.size());
        this.bufferManager();
    }

    public void save(User user) {
        //if cache size is max
        //add new email to cache and set dirty to true to add it to main database
        System.out.println("New User of id: " + user.getId());
        this.cache.put(user.getId(), new CacheBlock(user, System.currentTimeMillis(), true));
        if(!buffer.contains(user.getId()))
            this.buffer.add(user.getId());
        System.out.println("Cache size: " + this.cache.size());
        System.out.println("Buffer size: " + this.buffer.size());
        this.cacheManager();
        this.bufferManager();
    }

    public boolean exists(String email) {
        if(!this.cache.containsKey(email))
            return UserGateway.exists(email);
        return true;
    }

    public void bufferManager(){
        if(this.buffer.size() >= this.maxBufferSize)
            this.emptyBuffer.empty(this.cache, this.buffer, this.database);
    }

    public void cacheManager(){
        if(this.cache.size() >= this.maxSize)
            this.freeCacheSpace.free(this.cache);

    }

    public void remove(String userEmail){
        if(!this.cache.containsKey(userEmail))
            return;
        this.database.save(this.cache.get(userEmail).cachedObject);
        this.cache.remove(userEmail);
        while (this.buffer.contains(userEmail))
            this.buffer.remove(userEmail);
        System.out.println("Cache size: " + this.cache.size());
        System.out.println("Buffer size: " + this.buffer.size());
    }

}
