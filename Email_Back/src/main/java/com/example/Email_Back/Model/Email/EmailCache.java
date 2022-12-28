package com.example.Email_Back.Model.Email;

import java.util.HashMap;

public class EmailCache {

    private HashMap<String, Email> cache = new HashMap<String, Email>();

    private int maxSize = 300;
    private EmailDB database = null;

    public EmailCache(EmailDB db){
        this.database = db;
    }

    public Email retrieveEmail(String emailId){
        if(!cache.containsKey(emailId))
            this.loadFromMemory(emailId);
        this.database.closeMemory();
        return this.cache.get(emailId);
    }
    public Email[] retrieveEmail(String[] emailId){
        Email[] emails = new Email[emailId.length];
        for (int i = 0; i < emailId.length; i++){
            if(!cache.containsKey(emailId[i]))
                this.loadFromMemory(emailId[i]);
            emails[i] = cache.get(emailId[i]);
        }
        this.database.closeMemory();
        return emails;
    }

    public void updateEmail(Email updatedEmail){
        if(!cache.containsKey(updatedEmail.getId()))
            this.loadFromMemory(updatedEmail.getId());
        this.cache.put(updatedEmail.getId(), updatedEmail);
        this.database.closeMemory();
    }

    public void loadFromMemory(String emailId){
        //if(this.cache.size() == this.maxSize)
        //remove some elements
        this.cache.put(emailId, this.database.load(emailId));
    }

    public void deleteFromMemory(String emailId){
        this.cache.remove(emailId);
        this.database.deleteEmail(emailId);
        this.database.closeMemory();
    }
}
