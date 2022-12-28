package com.example.Email_Back.Model.Attachment;
import com.example.Email_Back.Utils.RandomGenerator;

public class Attachment {
    private String id;
    private String path;
    private String name;
    private String extension;


    public Attachment() {
        this.id = RandomGenerator.generateId();
        System.out.println(id);
    }

    //Getters Immutable
    public String getId() { return id; }

    public String getPath() { return path; }

    public String getName() { return name; }

    public String getExtension() { return extension; }

}