package com.example.Email_Back.Model.Attachment;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class AttachmentHandler {

    private final String directory = "database\\Attachments\\";
    private String fileName;
    private String extension;
    private byte[] BytesOfAttachment;

    public void LoadAttachment(Attachment ourAttachment) {
        String filePath = ourAttachment.getPath();
        fileName = ourAttachment.getName();
        extension = ourAttachment.getExtension();
        try(FileInputStream fin = new FileInputStream(filePath + fileName + extension)) {//TODO handle extension
            BytesOfAttachment = fin.readAllBytes();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void SaveToDB (String name) {
        try(FileOutputStream fos = new FileOutputStream(directory + name + extension)) {//TODO handle extension
            fos.write(BytesOfAttachment);
            System.out.println(BytesOfAttachment.length);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}