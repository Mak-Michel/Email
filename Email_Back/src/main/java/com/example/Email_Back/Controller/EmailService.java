package com.example.Email_Back.Controller;

import com.example.Email_Back.Model.Caches.UserCache;
import com.example.Email_Back.Model.Email.Email;
import com.example.Email_Back.Model.Caches.EmailCache;
import com.example.Email_Back.Model.Email.EmailBuilder;
import com.example.Email_Back.Model.Email.EmailHeader;
import com.example.Email_Back.Model.User.User;
import com.example.Email_Back.Model.User.UserHandler;
import com.example.Email_Back.Utils.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/email/")
@Component
public class EmailService {

    @Autowired
    private EmailCache emailCache;
    @Autowired
    private UserCache userCache;
    @Autowired
    private ApplicationContext appContext;

    @GetMapping("open")
    public ResponseEntity<Email> retrieveEmail(@RequestParam(value = "emailId") String emailId){
        System.out.println(emailId);
        return ResponseEntity.status(HttpStatus.OK).body(this.emailCache.retrieve(emailId));
    }

    @GetMapping("list")
    public ResponseEntity<EmailHeader[]> retrieveEmailList(@RequestParam(value = "userEmail") String userEmail, @RequestParam(value = "listType") String listType){
        System.out.println(userEmail + " " + listType);
        String[] userEmailsIDS = {};
        if(!userCache.exists(userEmail))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        User user = this.userCache.retrieve(userEmail);
        switch (listType) {
            case "inbox" -> userEmailsIDS = user.getReceivedEmailsIds().toArray(new String[0]);
            case "sent" -> userEmailsIDS = user.getSentEmailsIds().toArray(new String[0]);
            case "trash" -> userEmailsIDS = user.getTrashEmailsIds().toArray(new String[0]);
            case "draft" -> userEmailsIDS = user.getDraftEmailsIds().toArray(new String[0]);
            default -> {
                if (!user.getFolders().containsKey(listType))
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
                userEmailsIDS = user.getFolders().get(listType).toArray(new String[0]);
            }
        }
        EmailHeader[] headers = new EmailHeader[userEmailsIDS.length];
        Email[] userReceivedEmails = emailCache.retrieve(userEmailsIDS);
        for (int i = 0; i < userEmailsIDS.length; i++)
            System.out.println(userReceivedEmails[i]);
        for (int i = 0; i < userEmailsIDS.length; i++) {
            if(userReceivedEmails[userEmailsIDS.length - 1 - i] != null)
                headers[i] = userReceivedEmails[userEmailsIDS.length - 1 - i].createHeader();
        }
        return ResponseEntity.status(HttpStatus.OK).body(headers);
    }
    @PostMapping("new")
    public ResponseEntity<String> createNewEmail(@RequestBody Email newEmail){
        System.out.println("here");
        if(newEmail.getId() == null || newEmail.getId().length() != 15)
            newEmail.createId();
        newEmail.setDate(Integer.parseInt(LocalDate.now().toString().replace("-", "")));
        //check if users exits in the database
        //if not return empty response entity with HttpStatus error
        User sender = this.userCache.retrieve(newEmail.getSender()); //load user from database with id of newEmail.getSender()
        if(sender == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User " + newEmail.getSender() + " Not found");
        User[] receivers = new User[newEmail.getReceivers().size()]; //load user from database with id of newEmail.getReceivers()
        sender.getSentEmailsIds().add(newEmail.getId());
        sender.getDraftEmailsIds().remove(newEmail.getId());
        for(int i =  0; i < receivers.length; i++) {
            receivers[i] = this.userCache.retrieve(newEmail.getReceivers().get(i));
            if(receivers[i] == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User " + newEmail.getReceivers().get(i) + " Not found");
            receivers[i].getReceivedEmailsIds().add(newEmail.getId());
            this.userCache.update(receivers[i]);
        }
        System.out.println(newEmail.getId());
        this.userCache.update(sender);
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

    @DeleteMapping("deleteFromUser")
    public ResponseEntity<String> deleteEmail(@RequestParam(value = "userEmail") String userEmail, @RequestParam(value = "emailId") String emailId){
        System.out.println("Permenantly deleting " + emailId + " from user: " + userEmail);
        Email email = this.emailCache.retrieve(emailId);
        User user = this.userCache.retrieve(userEmail);
        user.getTrashEmailsIds().remove(emailId);
        this.userCache.update(user);
        if(email.delete())
            this.deleteFromDB(emailId);
        return ResponseEntity.status(HttpStatus.OK).body("Email Deleted Successfully");
    }

    @PutMapping("restoreEmail")
    public ResponseEntity<String> restoreEmail(@RequestParam("userEmail") String userEmail, @RequestParam("emailId") String emailId){
        User user = this.userCache.retrieve(userEmail);
        Email email = this.emailCache.retrieve(emailId);
        if(email.getSender().equals(userEmail)) {
            user.moveEmail("Trash", "Sent", emailId);
            System.out.println("Restoring " + emailId + " to Sent");
        }
        else {
            user.moveEmail("Trash", "Inbox", emailId);
            System.out.println("Restoring " + emailId + " to Inbox");
        }
        this.userCache.update(user);
        return ResponseEntity.status(HttpStatus.OK).body("Email Restored Successfully");
    }

    @PostMapping("draft")
    public ResponseEntity<String> createDraftEmail(@RequestBody String[] emailParameters){

        EmailBuilder eb = appContext.getBean(EmailBuilder.class);
        int numberOfReceivers;
        if(emailParameters[5] == null || emailParameters[5].equals(""))
            numberOfReceivers  = 0;
        else
            numberOfReceivers = Integer.parseInt(emailParameters[5]);
        System.out.println(numberOfReceivers);

        //int numberOfAttachments = Integer.parseInt(emailParameters[5 + numberOfReceivers]);

        if(emailParameters[0] != null && !emailParameters[0].equals(""))
            eb.setId(emailParameters[0]);
        else
            eb.setId(RandomGenerator.generateId());

        if(emailParameters[1] != null && !emailParameters[1].equals(""))
            eb.setSubject(emailParameters[1]);
        else
            eb.setSubject("");
        if(emailParameters[2] != null && !emailParameters[2].equals(""))
            eb.setEmailBody(emailParameters[2]);
        else
            eb.setEmailBody("");
        if(emailParameters[3] != null && !emailParameters[3].equals(""))
            eb.setPriority(Integer.parseInt(emailParameters[3]));
        else
            eb.setPriority(0);
        if(emailParameters[4] != null && !emailParameters[4].equals(""))
            eb.setSender(emailParameters[4]);
        else
            eb.setSender("");
        for(int i = 0; i < numberOfReceivers; i++)
            eb.addReceiver(emailParameters[6 + i]);
        /*for(int i = 0; i < numberOfAttachments; i++)
            eb.addAttachments_IDS(emailParameters[7 + numberOfReceivers + i]);*/

        //System.out.println(eb.getInstance().toString());
        Email newDraft = eb.getInstance();
        User sender = this.userCache.retrieve(newDraft.getSender());
        sender.getDraftEmailsIds().remove(newDraft.getId());
        sender.getDraftEmailsIds().add(newDraft.getId());
        this.userCache.update(sender);
        this.emailCache.add(newDraft);
        return ResponseEntity.status(HttpStatus.OK).body("Draft Saved");
    }

    @GetMapping("sort")
    public ResponseEntity<ArrayList<EmailHeader>> sort(@RequestParam(value = "userEmail") String userEmail, @RequestParam(value = "emailType") String emailType, @RequestParam(value = "sortType") String sortType)  {
        System.out.println(userEmail + " " + emailType + " " + sortType);
        String[] userEmailsIDS = {};
        if(!userCache.exists(userEmail))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        User user = this.userCache.retrieve(userEmail);
        switch (emailType) {
            case "inbox" -> userEmailsIDS = user.getReceivedEmailsIds().toArray(new String[0]);
            case "sent" -> userEmailsIDS = user.getSentEmailsIds().toArray(new String[0]);
            case "trash" -> userEmailsIDS = user.getTrashEmailsIds().toArray(new String[0]);
            case "draft" -> userEmailsIDS = user.getDraftEmailsIds().toArray(new String[0]);
            default -> {
                if (!user.getFolders().containsKey(emailType))
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
                userEmailsIDS = user.getFolders().get(emailType).toArray(new String[0]);
            }
        }
        ArrayList<Email> emails = (ArrayList<Email>) Arrays.asList(this.emailCache.retrieve(userEmailsIDS));
        for(int i = 0; i < emails.size(); i++)
            System.out.println(emails.get(i));
        Sort sorter = new Sort();
        sorter.sort(emails, sortType);
        for(int i = 0; i < emails.size(); i++)
            System.out.println(emails.get(i));
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
