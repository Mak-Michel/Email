package com.example.Email_Back.Model.Gateways;

import com.example.Email_Back.Model.Caches.Cacheable;
import com.example.Email_Back.Model.Email.Email;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.HashMap;

public class EmailGateway implements Gateway{

    private HashMap<String, Email> database = new HashMap<>();

    public void saveAll() {
        if(database.isEmpty()) {
            return;
        }
        try (FileOutputStream myFile = new FileOutputStream("database\\Emails\\MailDB.json")) {
            ObjectMapper mapper = new ObjectMapper();
            byte[] Obj = mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(database);
            myFile.write(Obj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Save Done");
    }

    public HashMap<String, Email> loadAll() {
        HashMap<String, Email> emails = null;
        try (FileReader myFile = new FileReader("database\\Emails\\MailDB.json")) {
            ObjectMapper mapper = new ObjectMapper();
            emails = mapper.readValue(myFile, new TypeReference<HashMap<String, Email>>() {});
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if(emails == null)
            emails = new HashMap<>();
        return emails;
    }

    public Email load(String id){
        if(this.database.isEmpty())
            this.database = this.loadAll();
        if(this.database.containsKey(id))
            return this.database.get(id);
        return null;
    }

    public void save(Cacheable email){
        if(this.database.isEmpty())
            this.database = this.loadAll();
        System.out.println(((Email) email).toString());
        this.database.put(email.getId(), (Email) email);
    }

    public void delete(String emailID){
        if(this.database.isEmpty())
            this.database = this.loadAll();
        this.database.remove(emailID);
    }

    public boolean isOpen(){
        return !this.database.isEmpty();
    }

    public void closeMemory(){
        this.database.clear();
    }
}