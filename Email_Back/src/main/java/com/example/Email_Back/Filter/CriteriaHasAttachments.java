package com.example.Email_Back.Filter;

import com.example.Email_Back.Model.Email.Email;
import java.util.ArrayList;

public class CriteriaHasAttachments implements ICriteria{

    private boolean WithAttachments;

    public CriteriaHasAttachments(boolean WithAttachments) {
        this.WithAttachments = WithAttachments;
    }

    public ArrayList<Email> meetCriteria(ArrayList<Email> emails) {
        ArrayList<Email> met = new ArrayList<>();
        if(WithAttachments) {
            for (Email email : emails)
                if (email.getAttachments_IDS().size() > 0)
                    met.add(email);
        }
        else {
            for (Email email : emails)
                if (email.getAttachments_IDS().size() == 0)
                    met.add(email);
        }

        return met;
    }
}
