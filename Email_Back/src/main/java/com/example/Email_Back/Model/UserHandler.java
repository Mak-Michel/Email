package com.example.Email_Back.Model;

import com.example.Email_Back.Controller.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class UserHandler {

    private String directory = "Users\\";
    private String extension = ".json";
    private User user = new User(null, null, null);

    public UserHandler(User user) {
        this.user = user;
    }

    public UserHandler(String email) {
        this.user.setUserEmail(email);
    }

    public void saveUser() {
        try (FileOutputStream myFile = new FileOutputStream(this.directory + this.user.getUserEmail() + this.extension)) {
            ObjectMapper mapper = new ObjectMapper();
            byte[] Obj = mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(this.user);
            myFile.write(Obj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public User loadUser() {
        Map<String, String> user = new HashMap<String, String>();
        try(FileReader myFile = new FileReader(this.directory + this.user.getUserEmail() + this.extension)) {
            ObjectMapper mapper = new ObjectMapper();
            user = mapper.readValue(myFile, new TypeReference<Map<String, String>>() {}); //TODO handle load
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        this.user.setName(user.get("name"));
        this.user.setUserEmail(user.get("userEmail"));
        this.user.setUserPassword(user.get("userPassword"));
        return this.user;
    }

    public boolean exists() {
        File f = new File(this.directory + this.user.getUserEmail() + this.extension);
        return f.exists();
    }

    public boolean rightPassword(String password) {
        Map<String, String> user = new HashMap<String, String>();
        try(FileReader myFile = new FileReader(this.directory + this.user.getUserEmail() + this.extension)) {
            ObjectMapper mapper = new ObjectMapper();
            user = mapper.readValue(myFile, new TypeReference<Map<String, String>>() {}); //TODO handle load
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return password.equals(user.get("userPassword"));
    }

}
