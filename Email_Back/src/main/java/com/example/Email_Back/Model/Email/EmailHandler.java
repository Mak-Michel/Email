package com.example.Email_Back.Model.Email;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.HashMap;

public class EmailHandler {

    public  static void saveEmails(HashMap<String, Email> emails) {
        try (FileOutputStream myFile = new FileOutputStream("database\\Emails\\emails.json")) {
            ObjectMapper mapper = new ObjectMapper();
            byte[] Obj = mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(emails);
            myFile.write(Obj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Save Done");
    }

    public static HashMap<String, Email> loadEmails() {
        HashMap<String, Email> emails;
        try (FileReader myFile = new FileReader("database\\Emails\\emails.json")) {
            ObjectMapper mapper = new ObjectMapper();
            emails = mapper.readValue(myFile, new TypeReference<HashMap<String, Email>>() {});
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        for (HashMap.Entry<String, Email> set : emails.entrySet())
            System.out.println(set.getValue().getSender());
        return emails;
    }
}