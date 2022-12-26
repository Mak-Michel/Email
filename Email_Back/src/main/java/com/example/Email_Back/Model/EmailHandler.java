package com.example.Email_Back.Model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;

public class EmailHandler {

    private String directory = "Emails\\";
    private String fileName = "emails";
    private String extension = ".json";
//    private ArrayList<Email> emails = new ArrayList<Email>;

//    public EmailHandler(Email email) {
//        this.emails.add(email);
//    }

//    public void saveEmails() {
//        try (FileOutputStream myFile = new FileOutputStream(this.directory + this.fileName + this.extension)) {
//            ObjectMapper mapper = new ObjectMapper();
//            byte[] Obj = mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(this.emails);
//            myFile.write(Obj);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }

//    public ArrayList<Email> loadEmails() {
//        try(FileReader myFile = new FileReader(this.directory + this.fileName + this.extension)) {
//            ObjectMapper mapper = new ObjectMapper();
//            this.emails = mapper.readValue(myFile, new TypeReference<ArrayList<Email>>() {}); //TODO handle load
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return null;
//        }
//        return this.emails;
//    }

//    public void addEmail(Email email) {
//        this.emails.add(email);
//    }

}
