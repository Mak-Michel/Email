package com.example.Email_Back.Controller;

import com.example.Email_Back.Model.Email.Email;
import com.example.Email_Back.Model.Email.EmailCache;
import com.example.Email_Back.Model.Email.EmailHeader;
import com.example.Email_Back.Model.User.User;
import com.example.Email_Back.Model.User.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/email/")
public class EmailService {

    @Autowired
    private EmailCache cache;

    @Autowired
    private UserHandler userHandler;

    @GetMapping("open")
    public ResponseEntity<Email> retrieveEmail(@RequestBody String emailId){
        return new ResponseEntity<>(this.cache.retrieveEmail(emailId), HttpStatus.OK);
    }

    @GetMapping("inbox")
    public ResponseEntity<EmailHeader[]> retrieveInbox(@RequestBody String userEmail){
        String[] userEmailsIDS = userHandler.getReceivedEmailsIds(userEmail).toArray(new String[0]);
        EmailHeader[] headers = new EmailHeader[userEmailsIDS.length];
        Email[] userReceivedEmails = cache.retrieveEmail(userEmailsIDS);
        for (int i = 0; i < userEmailsIDS.length; i++)
            headers[i] = userReceivedEmails[userEmailsIDS.length - 1 - i].createHeader();
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @GetMapping("sent")
    public ResponseEntity<EmailHeader[]> retrieveSent(@RequestBody String userEmail){
        String[] userEmailsIDS = this.userHandler.getSentEmailsIds(userEmail).toArray(new String[0]);
        EmailHeader[] headers = new EmailHeader[userEmailsIDS.length];
        Email[] userReceivedEmails = cache.retrieveEmail(userEmailsIDS);
        for (int i = 0; i < userEmailsIDS.length; i++)
            headers[i] = userReceivedEmails[userEmailsIDS.length - 1 - i].createHeader();
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @GetMapping("trashed")
    public ResponseEntity<EmailHeader[]> retrieveTrashed(@RequestBody String userEmail){
        String[] userEmailsIDS = this.userHandler.getTrashEmailsIds(userEmail).toArray(new String[0]);
        EmailHeader[] headers = new EmailHeader[userEmailsIDS.length];
        Email[] userReceivedEmails = cache.retrieveEmail(userEmailsIDS);
        for (int i = 0; i < userEmailsIDS.length; i++)
            headers[i] = userReceivedEmails[userEmailsIDS.length - 1 - i].createHeader();
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @GetMapping("draft")
    public ResponseEntity<EmailHeader[]> retrieveDraft(@RequestBody String userEmail){
        String[] userEmailsIDS = this.userHandler.getDraftEmailsIds(userEmail).toArray(new String[0]);
        EmailHeader[] headers = new EmailHeader[userEmailsIDS.length];
        Email[] userReceivedEmails = cache.retrieveEmail(userEmailsIDS);
        for (int i = 0; i < userEmailsIDS.length; i++)
            headers[i] = userReceivedEmails[userEmailsIDS.length - 1 - i].createHeader();
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @PostMapping("new")
    public ResponseEntity<String> createNewEmail(@RequestBody Email newEmail){
        newEmail.createId();
        //check if users exits in the database
        //if not return empty response entity with HttpStatus error
        User sender = this.userHandler.loadUser(newEmail.getSender()); //load user from database with id of newEmail.getSender()
        User[] receivers = new User[newEmail.getReceivers().length]; //load user from database with id of newEmail.getReceivers()
        sender.getSentEmailsIds().add(newEmail.getId());
        for(int i =  0; i < receivers.length; i++) {
            receivers[i] = this.userHandler.loadUser(newEmail.getReceivers()[i]);
            receivers[i].getReceivedEmailsIds().add(newEmail.getId());
        }
        return new ResponseEntity<>("Email sent successfully!!!", HttpStatus.CREATED);
    }

    public void deleteEmail(String emailId){
        if(this.cache.retrieveEmail(emailId).delete())
            this.cache.deleteFromMemory(emailId);
    }

    @PutMapping("edit")
    public ResponseEntity<String> updateEmail(@RequestBody Email editedEmail){
        this.cache.updateEmail(editedEmail);
        return new ResponseEntity<>("Email edited successfully!!!", HttpStatus.OK);
    }

}