package com.example.Email_Back.Controller;

import com.example.Email_Back.Model.Attachment.Attachment;
import com.example.Email_Back.Model.Attachment.AttachmentHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.swing.*;
import java.io.File;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/attachments/")
public class AttachmentService {

    AttachmentHandler currentHandler;

    public AttachmentService () {
        this.currentHandler = new AttachmentHandler();
    }

    @PostMapping("attach")
    public void UploadAttachment (@RequestBody Attachment ourAttachment) {
        currentHandler.LoadAttachment(ourAttachment);
        currentHandler.SaveToDB(ourAttachment.getId());
    }

    @GetMapping("open")
    public ResponseEntity<String> view() {
        System.setProperty("java.awt.headless","false");
        String path = null;
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        try {
            File myFile = chooser.getSelectedFile();
            path = myFile.getPath();
            System.out.println(path);
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(path);
    }

}