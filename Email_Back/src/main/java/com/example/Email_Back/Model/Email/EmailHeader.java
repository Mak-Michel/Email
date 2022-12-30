package com.example.Email_Back.Model.Email;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class EmailHeader {

    private String headerBody;
    private String sender;

    private String[] receiver;
    private String subject;
    private int date;
    private boolean read;

    public void setHeaderProperties(String headerBody, String sender, String[] receiver, String subject, int date, boolean read) {
        this.headerBody = headerBody;
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.date = date;
        this.read = read;
    }

    public String getHeaderBody() {
        return headerBody;
    }

    public void setHeaderBody(String headerBody) {
        this.headerBody = headerBody;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String[] getReceiver() {
        return receiver;
    }

    public void setReceiver(String[] receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
