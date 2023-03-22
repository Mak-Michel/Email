package com.example.Email_Back.Model.Caches;

import com.example.Email_Back.Model.Caches.Threads.EmptyBuffer;
import com.example.Email_Back.Model.Caches.Threads.FreeCacheSpace;
import com.example.Email_Back.Model.Email.Email;
import com.example.Email_Back.Model.Gateways.EmailGateway;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

@Component
public class EmailCache{

    private EmptyBuffer emptyBuffer = new EmptyBuffer();
    private FreeCacheSpace freeCacheSpace = new FreeCacheSpace();
    private HashMap<String, CacheBlock> cache = new HashMap<>();
    private Queue<String> buffer = new LinkedList<>();

    private EmailGateway database = new EmailGateway();

    private final int maxBufferSize = 20;
    private final int maxSize = 50;

    public void loadFromDB(String emailId){
        this.cache.put(emailId, new CacheBlock(this.database.load(emailId), System.currentTimeMillis()));
    }

    public void updateIntoDB(Email updatedEmail){
        database.save(updatedEmail);
    }

    public void deleteFromDB(String emailId){
        database.delete(emailId);
    }

    public void saveDBContents(){
        this.emptyBuffer.empty(this.cache, this.buffer, this.database);
    }

    public Email retrieve(String emailId){
        if(!cache.containsKey(emailId))
            this.loadFromDB(emailId);
        database.closeMemory();
        CacheBlock currentBlock = this.cache.get(emailId);
        currentBlock.counter = System.currentTimeMillis();
        this.cacheManager();
        return (Email) currentBlock.cachedObject;
    }
    public Email[] retrieve(String[] emailId){
        Email[] emails = new Email[emailId.length];
        for (int i = 0; i < emailId.length; i++){
            if(!cache.containsKey(emailId[i]))
                this.loadFromDB(emailId[i]);
            CacheBlock currentBlock = this.cache.get(emailId[i]);
            currentBlock.counter = System.currentTimeMillis();
            emails[i] = (Email) currentBlock.cachedObject;
        }
        this.cacheManager();
        this.database.closeMemory();
        return emails;
    }

    public void update(Email updatedEmail){
        //check if element in cache
        if(!cache.containsKey(updatedEmail.getId()))
            this.loadFromDB(updatedEmail.getId()); //if miss load it from memory
        //load the email into cache
        this.cache.put(updatedEmail.getId(), new CacheBlock(updatedEmail, System.currentTimeMillis(), true));
        //if it was a miss then directly update the email in main database
        if(database.isOpen())
            database.save(updatedEmail);
        //add id to buffer to be updated in DB
        this.buffer.add(updatedEmail.getId());
        this.bufferManager();
        //close access to main database
        database.closeMemory();
    }

    public void add(Email email){
        //if cache size is max
        //add new email to cache and set dirty to true to add it to main database
        System.out.println("New Email of id: " + email.getId());
        this.cache.put(email.getId(), new CacheBlock(email, System.currentTimeMillis(), true));
        System.out.println("Cache size is now: " + this.cache.size());
        this.buffer.add(email.getId());
        this.cacheManager();
        this.bufferManager();
    }

    public void delete(String emailId){
        //remove email from cache
        this.cache.remove(emailId);
        //add id to buffer
        this.buffer.add(emailId);
        this.bufferManager();
    }

    public void bufferManager(){
        if(this.buffer.size() >= this.maxBufferSize)
            this.emptyBuffer.empty(this.cache, this.buffer, this.database);
    }

    public void cacheManager(){
        if(this.cache.size() >= this.maxSize){
            if(!this.freeCacheSpace.free(this.cache))
                this.emptyBuffer.empty(this.cache, this.buffer, this.database);
        }


    }

}
