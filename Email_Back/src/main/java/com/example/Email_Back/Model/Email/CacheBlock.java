package com.example.Email_Back.Model.Email;

public class CacheBlock {

    public Email email;
    public long counter;

    public CacheBlock(Email email, long counter) {
        this.email = email;
        this.counter = counter;
    }
}