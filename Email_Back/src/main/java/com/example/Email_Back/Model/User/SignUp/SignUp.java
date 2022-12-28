package com.example.Email_Back.Model.User.SignUp;

import com.example.Email_Back.Model.User.UserCahce;
import com.example.Email_Back.Model.User.User;

public class SignUp implements ISignUp{

    private String name;
    private String email;
    private String password;

    private UserCahce cache;

    public SignUp(String name, String email, String password, UserCahce cache) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.cache = cache;
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
        User user = new User();
        user.setUserProperties(this.name, this.email, this.password);
        cache.saveUser(user);
        System.out.println("User saved successfully");
    }

}
