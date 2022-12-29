package com.example.Email_Back.Model.User.SignIn;

import com.example.Email_Back.Model.User.User;
import com.example.Email_Back.Model.User.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class ProxySignIn implements ISignIn{

    private ISignIn user;
    private String email;
    private final String mailExtension;
    private String password;

    private UserHandler userHandler;

    public ProxySignIn(String email, String password, UserHandler userHandler) {
        this.setEmail(email.substring(0, email.indexOf("@")));
        this.mailExtension = email.substring(email.indexOf("@"));
        this.setPassword(password);
        this.userHandler = userHandler;
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

    public String loadUser() {
        this.checkValidInputs();
        this.checkExistence();
        this.checkPassword();
        this.user = new SignIn(this.email, this.password, this.userHandler);
        return this.user.loadUser();
    }

    private void checkValidInputs() {
        if(!this.mailExtension.equals("@mymail.com"))
            throw new RuntimeException("Email is written in a wrong format!");
        if(this.password.equals(""))
            throw new RuntimeException("Password cannot be empty!");
    }

    private void checkExistence() {
        if(!this.userHandler.exists(this.email))
            throw new RuntimeException("User is not found!");
    }

    private void checkPassword() {
        String pass = userHandler.getPassword(this.email);
        if(!pass.equals(this.password))
            throw new RuntimeException("Password is incorrect!");
    }

}
