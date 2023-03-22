package com.example.Email_Back.Filter;

import com.example.Email_Back.Model.Email.Email;
import java.util.ArrayList;

public class CriteriaBodyLength implements ICriteria {

    private int BodyLengthLimit;
    private boolean Larger;

    public CriteriaBodyLength(int BodyLengthLimit, boolean Larger) {
        this.BodyLengthLimit = BodyLengthLimit;
        this.Larger = Larger;
    }


    public ArrayList<Email> meetCriteria(ArrayList<Email> emails) {
        ArrayList<Email> met = new ArrayList<>();
        if(Larger) {
            for (Email email : emails)
                if (email.getEmailBody().length() >= BodyLengthLimit)
                    met.add(email);
        }
        else {
            for (Email email : emails)
                if (email.getEmailBody().length() < BodyLengthLimit)
                    met.add(email);
        }

        return met;
    }
}
