package com.example.Email_Back.Model.Caches;

import com.example.Email_Back.Model.Caches.Threads.EmptyBuffer;
import com.example.Email_Back.Model.Caches.Threads.FreeCacheSpace;
import com.example.Email_Back.Model.Gateways.EmailGateway;
import com.example.Email_Back.Model.User.User;
import com.example.Email_Back.Model.Gateways.UserGateway;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

@Component
public class UserCache {

    private HashMap<String, CacheBlock> cache = new HashMap<>();
    private Queue<String> buffer = new LinkedList<>();

    private UserGateway database = new UserGateway();

    private final int maxBufferSize = 5;
    private final int maxSize = 10;

    public void loadFromDB(String email){
        this.cache.put(email, new CacheBlock(database.load(email), System.currentTimeMillis()));
    }

    public User retrieve(String email) {
        if(!this.cache.containsKey(email))
            this.loadFromDB(email);
        return (User) this.cache.get(email).cachedObject;
    }

    public void update(User updatedUser){
        //check if element in cache
        if(!cache.containsKey(updatedUser.getId()))
            this.loadFromDB(updatedUser.getId()); //if miss load it from memory
        //load the email into cache
        this.cache.put(updatedUser.getId(), new CacheBlock(updatedUser, System.currentTimeMillis(), true));
        //add id to buffer to be updated in DB
        this.buffer.add(updatedUser.getId());
        this.bufferManager();
    }

    public void save(User user) {
        //if cache size is max
        //add new email to cache and set dirty to true to add it to main database
        System.out.println("New User of id: " + user.getId());
        this.cache.put(user.getId(), new CacheBlock(user, System.currentTimeMillis(), true));
        System.out.println("Cache size is now: " + this.cache.size());
        this.buffer.add(user.getId());
        this.cacheManager();
        this.bufferManager();
    }

    public boolean exists(String email) {
        if(!this.cache.containsKey(email))
            return UserGateway.exists(email);
        return true;
    }

    public void bufferManager(){
        if(this.buffer.size() >= this.maxBufferSize) {
            EmptyBuffer emptyBuffer = new EmptyBuffer(this.cache, this.buffer, this.database);
            emptyBuffer.start();
        }
    }

    public void cacheManager(){
        if(this.cache.size() >= this.maxSize) {
            FreeCacheSpace freeCacheSpace = new FreeCacheSpace(this.cache);
            freeCacheSpace.start();
        }
    }

}
