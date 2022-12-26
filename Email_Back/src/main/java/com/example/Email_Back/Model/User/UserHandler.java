package com.example.Email_Back.Model.User;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class UserHandler {

    private String directory = "Users\\";
    private String extension = ".json";
    private User user = new User();

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
        try(FileReader myFile = new FileReader(this.directory + this.user.getUserEmail() + this.extension)) {
            ObjectMapper mapper = new ObjectMapper();
            this.user = mapper.readValue(myFile, new TypeReference<User>() {});
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return this.user;
    }

    public boolean exists() {
        File f = new File(this.directory + this.user.getUserEmail() + this.extension);
        return f.exists();
    }

    public boolean rightPassword(String password) {
        try(FileReader myFile = new FileReader(this.directory + this.user.getUserEmail() + this.extension)) {
            ObjectMapper mapper = new ObjectMapper();
            this.user = mapper.readValue(myFile,  new TypeReference<User>() {}); //TODO handle load password only
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return password.equals(this.user.getUserPassword());
    }

}
