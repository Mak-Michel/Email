package com.example.Email_Back.Filter;

import com.example.Email_Back.Model.Email.Email;

import java.util.ArrayList;

public interface ICriteria {
    public ArrayList<Email> meetCriteria(ArrayList<Email> emails);
}
