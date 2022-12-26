package com.example.Email_Back.Model.SignUp;

import com.example.Email_Back.Controller.User;
import com.example.Email_Back.Model.UserHandler;

public class SignUp implements ISignUp{

    private String name;
    private String email;
    private String password;

    public SignUp(String name, String email, String password) {
        this.setName(name);
        this.setEmail(email);
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
        User user = new User(this.name, this.email, this.password);
        UserHandler saver = new UserHandler(user);
        saver.saveUser();
        System.out.println("User saved successfully");
    }

}
