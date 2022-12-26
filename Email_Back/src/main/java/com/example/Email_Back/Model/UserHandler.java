package com.example.Email_Back.Model;

import com.example.Email_Back.Controller.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class UserHandler {

    private String directory = "";
    private String extension = ".json";
    private User user;

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
            this.user = mapper.readValue(myFile, new TypeReference<User>() {});
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return password == this.user.getUserPassword();
    }

}
