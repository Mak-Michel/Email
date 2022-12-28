package com.example.Email_Back.Model.User.SignIn;

import com.example.Email_Back.Model.User.UserCahce;
import com.example.Email_Back.Model.User.User;

public class SignIn implements ISignIn{

    private String email;
    private String password;

    private UserCahce cache;

    public SignIn(String email, String password, UserCahce cache) {
        this.setEmail(email);
        this.setPassword(password);
        this.cache = cache;
    }


    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public User loadUser() {
        User user;
        user = cache.loadUser(this.email);
        System.out.println("User loaded successfully");
        return user;
    }

}
