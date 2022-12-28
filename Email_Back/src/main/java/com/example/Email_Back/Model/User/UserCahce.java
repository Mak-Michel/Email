package com.example.Email_Back.Model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class UserCahce {

    private HashMap<String, User> users = new HashMap<String, User>();

    private int maxSize = 20;

    private UserHandler userHandler = new UserHandler();

    public void saveUser(User user) {
        users.put(user.getUserEmail(), user);
        userHandler = new UserHandler(user);
        userHandler.saveUser();
    }

    public User loadUser(String email) {
        if(!users.containsKey(email))
            users.put(email, userHandler.loadUser(email));
        return users.get(email);
    }

    public boolean exists(String email) {
        return userHandler.exists(email);
    }

    public String getName(String email) {
        if(!users.containsKey(email))
            users.put(email, userHandler.loadUser(email));
        return users.get(email).getName();
    }

    public String getPassword(String email) {
        if(!users.containsKey(email))
            users.put(email, userHandler.loadUser(email));
        return users.get(email).getUserPassword();
    }

}
