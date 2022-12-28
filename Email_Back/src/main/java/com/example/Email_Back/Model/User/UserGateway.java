package com.example.Email_Back.Model.User;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class UserGateway {

    private final String directory = "database\\Users\\";
    private final String extension = ".json";
    private User user;

    public UserGateway(User user) {
        this.user = user;
    }

    public UserGateway() {
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

    public User loadUser(String email) {
        this.user = new User();
        try(FileReader myFile = new FileReader(this.directory + email + this.extension)) {
            ObjectMapper mapper = new ObjectMapper();
            this.user = mapper.readValue(myFile, new TypeReference<>() {});
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return this.user;
    }

    public boolean exists(String email) {
        File f = new File(this.directory + email + this.extension);
        return f.exists();
    }

}
