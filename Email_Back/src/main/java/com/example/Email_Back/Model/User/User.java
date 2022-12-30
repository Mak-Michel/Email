package com.example.Email_Back.Model.User;

import com.example.Email_Back.Model.Caches.Cacheable;

import java.util.*;

public class User implements Cacheable {

    private String name = "";
    private String id = "";
    private String userPassword = "";
    private ArrayList<String> sentEmailsIds = new ArrayList<>();
    private ArrayList<String> receivedEmailsIds = new ArrayList<>();
    private ArrayList<String> trashEmailsIds = new ArrayList<>();
    private ArrayList<String> draftEmailsIds = new ArrayList<>();
    private HashMap<String, Contact> contacts = new HashMap<>();

    public void setUserProperties (String name, String id, String userPassword) {
        this.name = name;
        this.id = id;
        this.userPassword = userPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public ArrayList<String> getSentEmailsIds() {
        return sentEmailsIds;
    }

    public void setSentEmailsIds(ArrayList<String> sentEmailsIds) {
        this.sentEmailsIds = sentEmailsIds;
    }

    public ArrayList<String> getReceivedEmailsIds() {
        return receivedEmailsIds;
    }

    public void setReceivedEmailsIds(ArrayList<String> receivedEmailsIds) {
        this.receivedEmailsIds = receivedEmailsIds;
    }

    public ArrayList<String> getTrashEmailsIds() {
        return trashEmailsIds;
    }

    public void setTrashEmailsIds(ArrayList<String> trashEmailsIds) {
        this.trashEmailsIds = trashEmailsIds;
    }

    public ArrayList<String> getDraftEmailsIds() {
        return draftEmailsIds;
    }

    public void setDraftEmailsIds(ArrayList<String> draftEmailsIds) {
        this.draftEmailsIds = draftEmailsIds;
    }

    public HashMap<String, Contact> getContacts() {
        return contacts;
    }

    public void setContacts(HashMap<String, Contact> contacts) {
        this.contacts = contacts;
    }

    public void receiveEmail(String id) {
        this.receivedEmailsIds.add(id);
    }

    public void sendEmail(String id) {
        this.sentEmailsIds.add(id);
    }

    public void deleteEmail(String id) {
        this.trashEmailsIds.add(id);
        //TODO: delete the email from its location
    }

    public void restoreEmail(String id) {
        this.trashEmailsIds.remove(id);
        //TODO: restore the email to its location
    }

    public void draftEmail(String id) {
        this.draftEmailsIds.add(id);
    }

    public void addContact(String name, String email) {
        if(getContacts().containsKey(name))
            getContacts().get(getContacts().get(name)).addUserEmail(email);
        else
            getContacts().put(name, new Contact(name, email));
    }

    public void forwardEmail() {
        //TODO prototype
    }

}
