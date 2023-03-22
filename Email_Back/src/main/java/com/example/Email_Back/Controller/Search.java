package com.example.Email_Back.Controller;

import com.example.Email_Back.Model.Email.Email;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@Component
public class Search {

    public ArrayList<Email> search(ArrayList<Email> emails, String searchKey, String according) {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Email> result = new ArrayList<>();
        //int index;
        //Sort sorter = new Sort();
        //sorter.sort(emails, according);////////////
        switch(according) {
            case "sender": for(Email email: emails) list.add(email.getSender());
                break;
            case "receivers":
                for(Email email: emails) {
                    ArrayList<String>receivers = email.getReceivers();
                    for(String receiver: receivers) {
                        if(receiver.contains(searchKey)) {
                            result.add(email);
                            break;
                        }
                    }
                }
                return result;         //TODO be tested////////////////////////////////////////////////////////////////////////
            case "date": for(Email email: emails) list.add(String.valueOf(email.getDate()));
                break;
            case "subject": for(Email email: emails) list.add(email.getSubject());
                break;
            case "body": for(Email email: emails) list.add(email.getEmailBody());
                break;
            case "importance":
                for(Email email: emails)
                    if(email.getPriority() == Integer.parseInt(searchKey))
                        result.add(email);
                return result;
            //TODO Attachments///////////////////////////////////////////////////////////////////////////////////////
        }

        for(int i=0;i< list.size();i++)
            if(list.get(i).contains(searchKey))
                result.add(emails.get(i));




        /*index = Collections.binarySearch(list,searchKey);////////
        System.out.println(index);
        if(index < 0 || index > emails.size()-1) return result;
        result.add(emails.get(index));
        if(index != emails.size()-1){
            int j=index+1;/////
            while(j < emails.size() && list.get(j).equals(searchKey)) {
                System.out.println(j); result.add(emails.get(j)); j++; }//////
        }
        if(index != 0) {
            int k=index-1;////////
            while(k >= 0 && list.get(k).equals(searchKey)) { result.add(emails.get(k)); k--; }////////
        }*/
        return result;
    }

}