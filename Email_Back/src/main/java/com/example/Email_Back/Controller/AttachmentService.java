package com.example.Email_Back.Controller;

import com.example.Email_Back.Model.Attachment.Attachment;
import com.example.Email_Back.Model.Attachment.AttachmentHandler;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.swing.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/Attachments/")
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
        String AbsolutePath = null;
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        try {
            File myFile = chooser.getSelectedFile();
            AbsolutePath = myFile.getPath();
            System.out.println(AbsolutePath);
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        }
        Attachment newAttachment = new Attachment();
        newAttachment.setProperties(AbsolutePath);
        currentHandler.LoadAttachment(newAttachment);
        currentHandler.SaveToDB(newAttachment.getId());
        return ResponseEntity.status(HttpStatus.FOUND).body(AbsolutePath);
    }


    @PostMapping("upload")
    public String upload(@RequestParam("attachments") List<MultipartFile> filesList) {
        //System.out.println(filesList.size());

        for(MultipartFile file: filesList) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            //System.out.println(fileName);

            //Path filePath = get(System.getProperty("user.home") + "/uploadedfiles/", fileName).toAbsolutePath().normalize();
            Path filePath = get("database/Attachments", fileName).normalize();
            try {
                copy(file.getInputStream(), filePath, REPLACE_EXISTING);
            } catch (Exception e) {
                System.out.println("error at file"+ fileName);
                System.out.println(e.getMessage());
            }
        }


        return "uploaded successfully";
    }


    @GetMapping("download/{name}")
    public ResponseEntity<Resource> download (@PathVariable("name") String name)  {
        System.out.println(name);
        Path path = null;
        Resource data = null;
        String ContentType = null;
        try {
            //path = get(System.getProperty("user.home") + "/uploadedfiles/").toAbsolutePath().normalize().resolve(name);
            path = get("database/Attachments").normalize().resolve(name);

            data = new UrlResource(path.toUri());
            ContentType = Files.probeContentType(path);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(ContentType);

        return (ResponseEntity<Resource>) ResponseEntity.ok().contentType(MediaType.parseMediaType(ContentType)).body(data);
    }

}