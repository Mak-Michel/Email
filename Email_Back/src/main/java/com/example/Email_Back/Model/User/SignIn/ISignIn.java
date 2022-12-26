package com.example.Email_Back.Model.User.SignIn;

import com.example.Email_Back.Model.User.User;

public interface ISignIn {

    public String getEmail();

    public void setEmail(String email);

    public String getPassword();

    public void setPassword(String password);

    public User loadUser();

}
