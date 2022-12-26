package com.example.Email_Back.Model.User.SignIn;

import com.example.Email_Back.Model.User.User;
import com.example.Email_Back.Model.User.UserHandler;

public class ProxySignIn implements ISignIn{

    private ISignIn user;
    private String email;
    private String mailExtension;
    private String password;

    public ProxySignIn(String name, String email, String password) {
        this.setEmail(email.substring(0, email.indexOf("@")));
        this.mailExtension = email.substring(email.indexOf("@"));
        this.setPassword(password);
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

    public User loadUser() {
        this.checkValidInputs();
        this.checkExistence();
        this.checkPassword();
        this.user = new SignIn(this.email, this.password);
        return this.user.loadUser();
    }

    private void checkValidInputs() {
        if(!this.mailExtension.equals("@mymail.com"))
            throw new RuntimeException("Email is written in a wrong format!");
        if(this.password.equals(""))
            throw new RuntimeException("Password cannot be empty!");
    }

    private void checkExistence() {
        UserHandler check = new UserHandler(this.email);
        if(!check.exists())
            throw new RuntimeException("User is not found!");
    }

    private void checkPassword() {
        UserHandler check = new UserHandler(this.email);
        if(!check.rightPassword(this.password))
            throw new RuntimeException("Password is incorrect!");
    }

}
