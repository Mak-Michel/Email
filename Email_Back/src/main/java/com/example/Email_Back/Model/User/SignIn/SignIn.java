package com.example.Email_Back.Model.User.SignIn;

import com.example.Email_Back.Model.User.User;
import com.example.Email_Back.Model.User.UserHandler;

public class SignIn implements ISignIn{

    private String email;
    private String password;

    public SignIn(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
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
        UserHandler loader = new UserHandler(this.email);
        user = loader.loadUser();
        System.out.println("User loaded successfully");
        return user;
    }

}
