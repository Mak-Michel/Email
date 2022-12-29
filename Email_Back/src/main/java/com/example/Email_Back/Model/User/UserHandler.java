package com.example.Email_Back.Model.User;

import com.example.Email_Back.Model.Caches.UserCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserHandler {

    @Autowired
    private UserCache cache;

    public void saveUser(User user) {
        cache.save(user);
    }

    public User loadUser(String email) {
        return cache.retrieve(email);
    }

    public boolean exists(String email) {
        return cache.exists(email);
    }

    public String getPassword(String email) {
        return cache.retrieve(email).getUserPassword();
    }

    public ArrayList<String> getReceivedEmailsIds(String email) {
        return cache.retrieve(email).getReceivedEmailsIds();
    }

    public ArrayList<String> getSentEmailsIds(String email) {
        return cache.retrieve(email).getSentEmailsIds();
    }

    public ArrayList<String> getTrashEmailsIds(String email) {
        return cache.retrieve(email).getTrashEmailsIds();
    }

    public ArrayList<String> getDraftEmailsIds(String email) {
        return cache.retrieve(email).getDraftEmailsIds();
    }

}
