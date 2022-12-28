package com.example.Email_Back.Model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserHandler {

    @Autowired
    private UserCache cache;

    public void saveUser(User user) {
        cache.saveUser(user);
    }

    public User loadUser(String email) {
        return cache.loadUser(email);
    }

    public boolean exists(String email) {
        return cache.exists(email);
    }

    public String getPassword(String email) {
        return cache.loadUser(email).getUserPassword();
    }

    public ArrayList<String> getReceivedEmailsIds(String email) {
        return cache.loadUser(email).getReceivedEmailsIds();
    }

    public ArrayList<String> getSentEmailsIds(String email) {
        return cache.loadUser(email).getSentEmailsIds();
    }

    public ArrayList<String> getTrashEmailsIds(String email) {
        return cache.loadUser(email).getTrashEmailsIds();
    }

    public ArrayList<String> getDraftEmailsIds(String email) {
        return cache.loadUser(email).getDraftEmailsIds();
    }

}
