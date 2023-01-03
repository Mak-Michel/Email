package com.example.Email_Back.Model.Attachment;
import com.example.Email_Back.Utils.RandomGenerator;

public class Attachment {
    private String id;
    private String path;
    private String name;
    private String extension;


    public Attachment() {
        this.id = RandomGenerator.generateId();
    }

    public void setProperties(String AbsolutePath) {
        for(int i=AbsolutePath.length()-1; i>=0;i--) {
            if(AbsolutePath.charAt(i) == '.'){
                extension = AbsolutePath.substring(i);
                path = AbsolutePath.substring(0,i);
                break;
            }
        }
        for(int i=path.length()-1; i>=0;i--) {
            if(path.charAt(i) == '\\') {
                name = path.substring(i+1);
                path = path.substring(0,i+1);
                break;
            }
        }
    }

    //Getters Immutable
    public String getId() { return id; }

    public String getPath() { return path; }

    public String getName() { return name; }

    public String getExtension() { return extension; }

}