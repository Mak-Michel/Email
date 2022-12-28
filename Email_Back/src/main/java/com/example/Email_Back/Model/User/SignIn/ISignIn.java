package com.example.Email_Back.Model.User.SignIn;

import com.example.Email_Back.Model.User.User;

public interface ISignIn {

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    User loadUser();

}
