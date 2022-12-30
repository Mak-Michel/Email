package com.example.Email_Back.Controller;

import com.example.Email_Back.Model.Caches.UserCache;
import com.example.Email_Back.Model.Email.Email;
import com.example.Email_Back.Model.Caches.EmailCache;
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
    private EmailCache emailCache;
    @Autowired
    private UserCache userCache;

    @GetMapping("open")
    public ResponseEntity<Email> retrieveEmail(@RequestParam(value = "emailId") String emailId){
        return ResponseEntity.status(HttpStatus.OK).body(this.emailCache.retrieve(emailId));
    }

    @GetMapping("list")
    public ResponseEntity<EmailHeader[]> retrieveEmailList(@RequestParam(value = "userEmail") String userEmail, @RequestParam(value = "listType") String listType){
        System.out.println(userEmail + " " + listType);
        String[] userEmailsIDS = {};
        switch (listType){
            case "inbox":
                userEmailsIDS = this.userCache.retrieve(userEmail).getReceivedEmailsIds().toArray(new String[0]);
                break;
            case "sent":
                userEmailsIDS = this.userCache.retrieve(userEmail).getSentEmailsIds().toArray(new String[0]);
                break;
            case "trashed":
                userEmailsIDS = this.userCache.retrieve(userEmail).getTrashEmailsIds().toArray(new String[0]);
                break;
            case "draft":
                userEmailsIDS = this.userCache.retrieve(userEmail).getDraftEmailsIds().toArray(new String[0]);
                break;
            default:
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        EmailHeader[] headers = new EmailHeader[userEmailsIDS.length];
        Email[] userReceivedEmails = emailCache.retrieve(userEmailsIDS);
        for (int i = 0; i < userEmailsIDS.length; i++)
            headers[i] = userReceivedEmails[userEmailsIDS.length - 1 - i].createHeader();
        return ResponseEntity.status(HttpStatus.OK).body(headers);
    }
    @PostMapping("new")
    public ResponseEntity<String> createNewEmail(@RequestBody Email newEmail){
        newEmail.createId();
        //check if users exits in the database
        //if not return empty response entity with HttpStatus error
        User sender = this.userCache.retrieve(newEmail.getSender()); //load user from database with id of newEmail.getSender()
        if(sender == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User " + newEmail.getSender() + " Not found");
        User[] receivers = new User[newEmail.getReceivers().length]; //load user from database with id of newEmail.getReceivers()
        sender.getSentEmailsIds().add(newEmail.getId());
        for(int i =  0; i < receivers.length; i++) {
            receivers[i] = this.userCache.retrieve(newEmail.getReceivers()[i]);
            if(receivers[i] == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User " + newEmail.getReceivers()[i] + " Not found");
            receivers[i].getReceivedEmailsIds().add(newEmail.getId());
            this.userCache.update(receivers[i]);
        }
        System.out.println(newEmail.getId());
        this.emailCache.add(newEmail);
        return ResponseEntity.status(HttpStatus.CREATED).body("Email Created successfully!!!");
    }

    @PutMapping("edit")
    public ResponseEntity<String> updateEmail(@RequestBody Email editedEmail){
        this.emailCache.update(editedEmail);
        return ResponseEntity.status(HttpStatus.OK).body("Email edited successfully!!!");
    }

    public ResponseEntity<String> deleteFromDB(String emailId){
        this.emailCache.delete(emailId);
        return new ResponseEntity<>("Email Deleted successfully!!!", HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteEmail(@RequestParam(value = "emailId") String emailId, @RequestParam(value = "userEmail") String userEmail){
        Email email = this.emailCache.retrieve(emailId);
        User user = this.userCache.retrieve(userEmail);
        if(user.getReceivedEmailsIds().contains(emailId))
            user.getReceivedEmailsIds().remove(emailId);
        else if(user.getSentEmailsIds().contains(email.getId()))
            user.getSentEmailsIds().remove(emailId);
        else if(user.getDraftEmailsIds().contains(email.getId()))
            user.getDraftEmailsIds().remove(emailId);
        else if(user.getTrashEmailsIds().contains(email.getId()))
            user.getTrashEmailsIds().remove(emailId);

        this.userCache.update(user);
        if(email.delete())
            this.deleteFromDB(emailId);

        return ResponseEntity.status(HttpStatus.OK).body("Email Deleted successfully!!!");
    }

    @PutMapping("trash")
    public ResponseEntity<String> trashEmail(@RequestParam(value = "emailId") String emailId, @RequestParam(value = "userEmail") String userEmail){
        User user = this.userCache.retrieve(userEmail);
        if(user.getReceivedEmailsIds().contains(emailId))
            user.getReceivedEmailsIds().remove(emailId);
        else if(user.getSentEmailsIds().contains(emailId))
            user.getSentEmailsIds().remove(emailId);
        else if(user.getDraftEmailsIds().contains(emailId))
            user.getDraftEmailsIds().remove(emailId);

        user.getTrashEmailsIds().add(emailId);

        this.userCache.update(user);
        return ResponseEntity.status(HttpStatus.OK).body("Email Deleted successfully!!!");
    }

}
