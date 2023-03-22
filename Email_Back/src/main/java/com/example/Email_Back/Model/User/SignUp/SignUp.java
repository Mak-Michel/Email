package com.example.Email_Back.Model.User.SignUp;

import com.example.Email_Back.Model.User.User;
import com.example.Email_Back.Model.User.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class SignUp implements ISignUp{


    private String name;
    private String email;
    private String password;

    private UserHandler userHandler;

    public SignUp(String name, String email, String password, UserHandler userHandler) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.userHandler = userHandler;
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

    public String addUser() {
        User user = new User();
        user.setUserProperties(this.name, this.email, this.password);
        userHandler.saveUser(user);
        System.out.println("User saved successfully");
        return this.email;
    }

}
