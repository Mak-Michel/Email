package com.example.Email_Back.Model.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class EmailCache{

    private HashMap<String, CacheBlock> cache = new HashMap<>();

    private int maxSize = 5;

    public String toRemove;
    public long max = 0;

    @Autowired
    private EmailDB database;

    public HashMap<String, CacheBlock> getCache() {
        return cache;
    }

    public Email retrieveEmail(String emailId){
        if(!cache.containsKey(emailId))
            this.loadFromMemory(emailId);
        this.database.closeMemory();
        CacheBlock currentBlock = this.cache.get(emailId);
        currentBlock.counter = System.currentTimeMillis();
        return currentBlock.email;
    }
    public Email[] retrieveEmail(String[] emailId){
        Email[] emails = new Email[emailId.length];
        for (int i = 0; i < emailId.length; i++){
            if(!cache.containsKey(emailId[i]))
                this.loadFromMemory(emailId[i]);
            CacheBlock currentBlock = this.cache.get(emailId[i]);
            currentBlock.counter = System.currentTimeMillis();
            emails[i] = currentBlock.email;
        }
        this.database.closeMemory();
        return emails;
    }

    public void updateEmail(Email updatedEmail){
        if(!cache.containsKey(updatedEmail.getId()))
            this.loadFromMemory(updatedEmail.getId());
        this.cache.put(updatedEmail.getId(), new CacheBlock(updatedEmail, 0));
        this.database.closeMemory();
    }

    public void loadFromMemory(String emailId){
        if(this.cache.size() == this.maxSize) {
            CacheWatcher watcher = new CacheWatcher(this.cache);
            watcher.start();
        }
        this.cache.put(emailId, new CacheBlock(this.database.load(emailId), 0));
    }

    public void addEmail(Email email){
        if(this.cache.size() == this.maxSize) {
            CacheWatcher watcher = new CacheWatcher(this.cache);
            watcher.start();
        }
        this.cache.put(email.getId(), new CacheBlock(email, System.currentTimeMillis()));
    }

    public void deleteFromMemory(String emailId){
        this.cache.remove(emailId);
        this.database.deleteEmail(emailId);
        this.database.closeMemory();
    }
}
