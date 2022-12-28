package com.example.Email_Back.Model.Email;

public class EmailHeader {

    private final String headerBody;
    private final String sender;

    private final String[] receiver;
    private final String subject;
    private final int date;
    private final boolean read;

    public EmailHeader(String headerBody, String sender, String[] receiver, String subject, int date, boolean read) {
        this.headerBody = headerBody;
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.date = date;
        this.read = read;
    }
}
