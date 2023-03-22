package com.example.Email_Back.Filter;

import com.example.Email_Back.Model.Email.Email;

import java.util.ArrayList;

public class CriteriaDate implements ICriteria {

    private String SpecifiedDate;
    private boolean Larger;

    public CriteriaDate(String SpecifiedDate, boolean Larger) {
        this.SpecifiedDate = SpecifiedDate;
        this.Larger = Larger;
    }


    public ArrayList<Email> meetCriteria(ArrayList<Email> emails) {
        ArrayList<Email> met = new ArrayList<>();
        if(Larger) {
            for (Email email : emails) {
                System.out.println(Integer.parseInt(email.getDate().replaceAll("-", "")) + 4);
                if (Integer.parseInt(email.getDate().replaceAll("-", "")) >= Integer.parseInt(SpecifiedDate))
                    met.add(email);
            }
        }
        else {
            for (Email email : emails)
                if (Integer.parseInt(email.getDate().replaceAll("-", "")) < Integer.parseInt(SpecifiedDate))
                    met.add(email);
        }

        return met;
    }



}
