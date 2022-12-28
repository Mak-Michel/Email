package com.example.Email_Back.Model.User;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class UserCache {

    private HashMap<String, User> users = new HashMap<String, User>();

    private int maxSize = 20;

    private UserGateway userGateway = new UserGateway();

    public void saveUser(User user) {
        users.put(user.getUserEmail(), user);
        System.out.println(user.getUserEmail());
        userGateway = new UserGateway(user);
        userGateway.saveUser();
    }

    public User loadUser(String email) {
        if(!users.containsKey(email))
            users.put(email, userGateway.loadUser(email));
        return users.get(email);
    }

    public boolean exists(String email) {
        return userGateway.exists(email);
    }

}
