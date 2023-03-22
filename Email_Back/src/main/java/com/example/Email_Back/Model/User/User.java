package com.example.Email_Back.Model.User;

import com.example.Email_Back.Model.Caches.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

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

    private HashMap<String, ArrayList<String>> folders = new HashMap<>();

    public void setUserProperties (String name, String id, String userPassword) {
        this.name = name;
        this.id = id;
        this.userPassword = userPassword;
    }

    public HashMap<String, ArrayList<String>> getFolders() {
        return folders;
    }

    public void setFolders(HashMap<String, ArrayList<String>> folders) {
        this.folders = folders;
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
        if(contacts.containsKey(name))
            contacts.get(name).addUserEmail(email);
        else
            getContacts().put(name, new Contact(name, email));
    }

    public void editContact(Contact editedContact) {
        if(contacts.containsKey(editedContact.getName()))
            contacts.put(editedContact.getName(), editedContact);
        if(editedContact.getUserEmails().size() == 0)
            contacts.remove(editedContact.getName());
    }

    public void forwardEmail() {
        //TODO prototype
    }

    public void addNewFolder(String folderName){
        if(this.folders.containsKey(folderName))
            return;
        this.folders.put(folderName, new ArrayList<String>());
    }

    public void deleteFolder(String folderName){
        if(!this.folders.containsKey(folderName))
            return;
        this.folders.remove(folderName);
    }

    public boolean moveEmail(String sourceFolder, String destinationFolder, String emailId){
        switch (destinationFolder) {
            case "Inbox" -> {
                this.getReceivedEmailsIds().add(emailId);
                System.out.println("moved To Inbox");
            }
            case "Sent" -> {
                this.getSentEmailsIds().add(emailId);
                System.out.println("moved to Sent");
            }
            case "Trash" -> {
                this.getTrashEmailsIds().add(emailId);
                System.out.println("moved to Trash");
            }
            case "Draft" -> {
                this.getDraftEmailsIds().add(emailId);
                System.out.println("moved to Draft");
            }
            default -> {
                if(!this.folders.containsKey(destinationFolder))
                    return false;
                System.out.println("moved to " + destinationFolder);
                this.folders.get(destinationFolder).add(emailId);
            }
        }
        switch (sourceFolder) {
            case "Inbox" -> {
                this.getReceivedEmailsIds().remove(emailId);
                System.out.println("moved from Inbox");
            }
            case "Sent" -> {
                this.getSentEmailsIds().remove(emailId);
                System.out.println("moved from Sent");
            }
            case "Trash" -> {
                this.getTrashEmailsIds().remove(emailId);
                System.out.println("moved from Trash");
            }
            case "Draft" -> {
                this.getDraftEmailsIds().remove(emailId);
                System.out.println("moved from Draft");
            }
            default -> {
                if(!this.folders.containsKey(sourceFolder))
                    return false;
                this.folders.get(sourceFolder).remove(emailId);
            }
        }
        return true;
    }

}
