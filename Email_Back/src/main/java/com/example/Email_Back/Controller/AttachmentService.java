package com.example.Email_Back.Controller;

import com.example.Email_Back.Model.Attachment.Attachment;
import com.example.Email_Back.Model.Attachment.AttachmentHandler;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/Attachments/")
public class AttachmentService {

    AttachmentHandler currentHandler;


    public AttachmentService () {
        this.currentHandler = new AttachmentHandler();
    }

    @PostMapping("attach")
    public String UploadAttachment (@RequestBody Attachment ourAttachment) {
        currentHandler.LoadAttachment(ourAttachment);
        currentHandler.SaveToDB(ourAttachment.getId() + "_" + ourAttachment.getName());
        return ourAttachment.getId() + "_" + ourAttachment.getName();
    }

}