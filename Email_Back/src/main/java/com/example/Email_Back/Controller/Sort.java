package com.example.Email_Back.Controller;

import com.example.Email_Back.Model.Email.Email;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sort {
    public void sort(ArrayList<Email> emails, String according) {
        switch(according) {
            case "sender": Collections.sort(emails, Comparator.comparing(Email::getSender));
                break;
            case "receivers": Collections.sort(emails, Comparator.comparing(Email::receiversNumber));
                break;
            case "date": Collections.sort(emails, Comparator.comparing(Email::getDate));
                break;
            case "subject": Collections.sort(emails, Comparator.comparing(Email::getSubject));
                break;
            case "body": Collections.sort(emails, Comparator.comparing(Email::getEmailBody));
                break;
            case "importance": Collections.sort(emails, Comparator.comparing(Email::getPriority));
                break;
            case "attachments": Collections.sort(emails, Comparator.comparing(Email::attachmentsNumber));
                break;
        }
    }
}