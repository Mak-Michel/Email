package com.example.Email_Back.Model.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;


@Component
@Scope(value = "prototype")
public class EmailBuilder {

    @Autowired
    private ApplicationContext appContext;
    private String id;
    private String emailBody;
    private String sender;
    private ArrayList<String> receivers = new ArrayList<>();
    private String subject;
    private ArrayList<String> attachments_IDS = new ArrayList<>();
    private int priority;

    public int getPriority() {
        return priority;
    }

    public String getId() {
        return id;
    }

    public EmailBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public EmailBuilder setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public EmailBuilder setEmailBody(String emailBody) {
        this.emailBody = emailBody;
        return this;
    }

    public String getSender() {
        return sender;
    }

    public EmailBuilder setSender(String sender) {
        this.sender = sender;
        return this;
    }

    public ArrayList<String> getReceivers() {
        return receivers;
    }

    public EmailBuilder addReceiver(String receivers) {
        this.receivers.add(receivers);
        return this;
    }

    public EmailBuilder setReceivers(String[] receivers) {
        this.receivers = (ArrayList<String>) Arrays.asList(receivers);
        return this;
    }

    public EmailBuilder setReceivers(ArrayList<String> receivers) {
        this.receivers = receivers;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public EmailBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public ArrayList<String> getAttachments_IDS() {
        return attachments_IDS;
    }

    public EmailBuilder setAttachments_IDS(String[] attachments_IDS) {
        this.attachments_IDS = (ArrayList<String>) Arrays.asList(attachments_IDS);
        return this;
    }

    public EmailBuilder setAttachments_IDS(ArrayList<String> attachments_IDS) {
        this.attachments_IDS = attachments_IDS;
        return this;
    }

    public EmailBuilder addAttachments_IDS(String attachment_ID) {
        this.attachments_IDS.add(attachment_ID);
        return this;
    }

    public Email getInstance() {
        Email email = appContext.getBean(Email.class);
        email.setEmailProperties(this.id, this.emailBody, LocalDate.now().toString(), this.sender, this.receivers, this.subject, this.attachments_IDS);
        return email;
    }
}
