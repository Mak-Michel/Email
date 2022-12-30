package com.example.Email_Back.Model.User.SignIn;

import com.example.Email_Back.Model.User.User;
import com.example.Email_Back.Model.User.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class SignIn implements ISignIn{

    private String email;
    private String password;

    private UserHandler userHandler;

    public SignIn(String email, String password, UserHandler userHandler) {
        this.setEmail(email);
        this.setPassword(password);
        this.userHandler = userHandler;
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

    public String loadUser() {
        userHandler.loadUser(this.email);
        System.out.println("User loaded successfully");
        return this.email;
    }

}
