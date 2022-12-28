package com.example.Email_Back.Model.User.SignUp;

import com.example.Email_Back.Model.User.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class ProxySignUp implements ISignUp{

    private ISignUp user;
    private String name;
    private String email;
    private final String mailExtension;
    private String password;

    private UserHandler userHandler;

    public ProxySignUp(String name, String email, String password, UserHandler userHandler) {
        this.setName(name);
        this.setEmail(email.substring(0, email.indexOf("@")));
        this.mailExtension = email.substring(email.indexOf("@"));
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

    public void addUser() {
        this.checkValidInputs();
        this.checkExistence();
        this.user = new SignUp(this.name, this.email, this.password, this.userHandler);
        this.user.addUser();
    }

    private void checkValidInputs() {
        if(!this.mailExtension.equals("@mymail.com"))
            throw new RuntimeException("Email is written in a wrong format!");
        if(this.name.equals(""))
            throw new RuntimeException("Name cannot be empty!");
        if(this.password.equals(""))
            throw new RuntimeException("Password cannot be empty!");
    }

    private void checkExistence() {
        if(userHandler.exists(this.email))
            throw new RuntimeException("User already exists!");
    }

}
