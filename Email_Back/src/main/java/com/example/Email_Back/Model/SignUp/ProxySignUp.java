package com.example.Email_Back.Model.SignUp;

import com.example.Email_Back.Model.UserHandler;

import java.io.File;

public class ProxySignUp implements ISignUp{

    private ISignUp user;
    private String name;
    private String email;
    private String password;

    public ProxySignUp(String name, String email, String password) {
        this.setName(name);
        this.setEmail(email.substring(0, email.indexOf("@")));
        this.setPassword(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addUser() {
        this.checkExistence();
        this.user = new SignUp(this.name, this.email, this.password);
        this.user.addUser();
    }

    private void checkExistence() {
        UserHandler check = new UserHandler(this.email);
        if(check.exists())
            throw new RuntimeException("User already exists!");
    }

}
