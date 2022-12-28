package com.example.Email_Back.Model.Email;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class EmailDB {

    private HashMap<String, Email> database;

    public void loadDatabase(){
        this.database = EmailHandler.loadEmails();
    }
    public Email load(String id){
        if(this.database.isEmpty())
            this.database = EmailHandler.loadEmails();
        if(this.database.containsKey(id))
            return database.get(id);
        return null;
    }

    public void closeMemory(){
        this.database.clear();
    }

    public void deleteEmail(String emailID){
        if(this.database.isEmpty())
            this.database = EmailHandler.loadEmails();
        this.database.remove(emailID);
    }
}
