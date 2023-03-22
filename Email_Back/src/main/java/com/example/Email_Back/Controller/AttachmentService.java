package com.example.Email_Back.Controller;

import com.example.Email_Back.Utils.RandomGenerator;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/Attachments/")
public class AttachmentService {

    @PostMapping("upload")
    public ResponseEntity<String[]> upload(@RequestParam("attachments") List<MultipartFile> filesList) {
        //System.out.println(filesList.size());

        String[] fileNames = new String[filesList.size()];
        for (int i = 0; i < filesList.size(); i++) {
            fileNames[i] = RandomGenerator.generateId() + StringUtils.cleanPath(filesList.get(i).getOriginalFilename());

            //Path filePath = get(System.getProperty("user.home") + "/uploadedfiles/", fileName).toAbsolutePath().normalize();
            Path filePath = get("database/Attachments", fileNames[i]).normalize();
            try {
                copy(filesList.get(i).getInputStream(), filePath, REPLACE_EXISTING);
            } catch (Exception e) {
                System.out.println("error at file" + fileNames[i]);
                System.out.println(e.getMessage());
            }
        }
        return ResponseEntity.ok().body(fileNames);
    }


    @GetMapping("download/{name}")
    public ResponseEntity<Resource> download(@PathVariable("name") String name) throws IOException {
        System.out.println("downloading...");
        Path filePath = get("database/Attachments/").toAbsolutePath().normalize().resolve(name);
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException(name + " was not found on the server");
        }
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        System.out.println(name);
        httpHeaders.add("File-Name", name);
        httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath))).headers(httpHeaders).body(resource);
    }
}