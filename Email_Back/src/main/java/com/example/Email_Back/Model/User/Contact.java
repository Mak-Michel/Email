package com.example.Email_Back.Model.User;

import java.util.ArrayList;

public class Contact {

    private String name;
    private ArrayList<String> userEmails;

    public Contact(String name, String email) {
        this.name = name;
        this.userEmails.add(email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getUserEmails() {
        return userEmails;
    }

    public void setUserEmails(ArrayList<String> userEmails) {
        this.userEmails = userEmails;
    }

    public void addUserEmail(String email) {
        this.userEmails.add(email);
    }

}
