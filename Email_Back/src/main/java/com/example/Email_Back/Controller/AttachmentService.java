package com.example.Email_Back.Controller;

import com.example.Email_Back.Model.Attachment.Attachment;
import com.example.Email_Back.Model.Attachment.AttachmentHandler;
import org.springframework.web.bind.annotation.*;
import javax.swing.*;
import java.io.File;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/Attachments/")
public class AttachmentService {

    AttachmentHandler currentHandler;

    @PostMapping("attach")
    public void UploadAttachment (@RequestBody Attachment ourAttachment) {
        currentHandler.LoadAttachment(ourAttachment);
        currentHandler.SaveToDB(ourAttachment.getId());
    }

    @GetMapping("open")
    public String view() {
        System.setProperty("java.awt.headless","false");
        String path = null;
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        try {
            File myFile = chooser.getSelectedFile();
            path = myFile.getPath();
            System.out.println(path);
        } catch (Exception e) {

        }
        return path;
    }

}