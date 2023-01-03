package com.example.Email_Back.Model.Email;

import com.example.Email_Back.Model.Caches.Cacheable;
import com.example.Email_Back.Utils.RandomGenerator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Scope("prototype")
public class Email implements Cacheable {
    private int importance;
    private String id;
    private String emailBody;
    private int date;
    private String sender;
    private ArrayList<String> receivers;
    private String subject;
    private ArrayList<String> attachments_IDS;

    private int priority;
    private char counter = 0;

    private boolean read;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setEmailProperties(Email email){
        this.id = RandomGenerator.generateId();
        this.emailBody = email.emailBody;
        this.date = email.date;
        this.sender = email.sender;
        this.receivers = email.receivers;
        this.subject = email.subject;
        this.attachments_IDS = email.attachments_IDS;
        this.read = false;
    }

    public void setEmailProperties(String id, String emailBody, int date, String sender, ArrayList<String> receivers, String subject, ArrayList<String> attachments_IDS) {
        this.importance = importance;
        this.id = id;
        this.emailBody = emailBody;
        this.date = date;
        this.sender = sender;
        this.receivers = receivers;
        this.subject = subject;
        this.attachments_IDS = attachments_IDS;
        this.read = false;
    }

    public void createId() {
        this.id = RandomGenerator.generateId();
    }
    public String getId() {
        return id;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setDate(int date) {
        this.date =  date;
    }
    public int getDate() {
        return date;
    }

    public String getSender() {
        return sender;
    }

    public ArrayList<String> getReceivers() {
        return receivers;
    }

    public String getSubject() {
        return subject;
    }

    public ArrayList<String> getAttachments_IDS() {
        return attachments_IDS;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead() {
        this.read = true;
    }

    public EmailHeader createHeader(){
        EmailHeader header = new EmailHeader();
        if(this.emailBody.length() < 50)
            header.setHeaderProperties(this.id, this.emailBody, this.sender, this.receivers, this.subject, this.date, this.read);
        else
            header.setHeaderProperties(this.id, this.emailBody.substring(0,50), this.sender, this.receivers, this.subject, this.date, this.read);
        return header;
    }

    public boolean delete(){
        this.counter++;
        return counter == this.receivers.size() + 1;
    }

    public char getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id='" + id + '\'' +
                ", emailBody='" + emailBody + '\'' +
                ", date=" + date +
                ", sender='" + sender + '\'' +
                ", receivers=" + receivers +
                ", subject='" + subject + '\'' +
                ", attachments_IDS=" + attachments_IDS +
                ", priority=" + priority +
                ", counter=" + counter +
                ", read=" + read +
                '}';
    }
    public int getImportance() { return importance; }

    public void setImportance(int importance) { this.importance = importance; }

    public int getNumberOfReceivers() { return receivers.size(); }
    public int getNumberOfAttachments() { return attachments_IDS.size(); }
}
