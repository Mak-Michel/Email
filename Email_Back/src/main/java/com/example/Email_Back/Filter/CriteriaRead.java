package com.example.Email_Back.Filter;

import com.example.Email_Back.Model.Email.Email;

import java.util.ArrayList;

public class CriteriaRead implements ICriteria {

    private boolean read;

    public CriteriaRead(boolean read) {
        this.read = read;
    }


    public ArrayList<Email> meetCriteria(ArrayList<Email> emails) {
        ArrayList<Email> met = new ArrayList<>();
        if(read) {
            for(Email email: emails)
                if(email.isRead())
                    met.add(email);
        }
        else {
            for(Email email: emails)
                if(!email.isRead())
                    met.add(email);
        }

        return met;
    }
}
