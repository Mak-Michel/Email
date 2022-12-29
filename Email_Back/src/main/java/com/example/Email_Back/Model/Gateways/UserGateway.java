package com.example.Email_Back.Model.Gateways;

import com.example.Email_Back.Model.Caches.Cacheable;
import com.example.Email_Back.Model.User.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class UserGateway implements Gateway{

    public User load(String email) {
        User user;
        try(FileReader myFile = new FileReader("database\\Users\\" + email + ".json")) {
            ObjectMapper mapper = new ObjectMapper();
            user = mapper.readValue(myFile, new TypeReference<User>() {});
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return user;
    }

    public void save(Cacheable user) {
        try (FileOutputStream myFile = new FileOutputStream("database\\Users\\" + user.getId() + ".json")) {
            ObjectMapper mapper = new ObjectMapper();
            byte[] Obj = mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes((User)user);
            myFile.write(Obj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(String email){}

    public static boolean exists(String email) {
        File f = new File("database\\Users\\" + email + ".json");
        return f.exists();
    }

}
