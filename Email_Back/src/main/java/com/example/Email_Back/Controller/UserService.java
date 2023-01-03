package com.example.Email_Back.Controller;

import com.example.Email_Back.Model.Caches.UserCache;
import com.example.Email_Back.Model.Email.Email;
import com.example.Email_Back.Model.User.Contact;
import com.example.Email_Back.Model.User.SignIn.ProxySignIn;
import com.example.Email_Back.Model.User.SignUp.ProxySignUp;
import com.example.Email_Back.Model.User.User;
import com.example.Email_Back.Model.User.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/user/")
public class UserService {

    @Autowired
    private UserHandler userHandler;
    @Autowired
    private UserCache userCache;

    @PostMapping("signUp")
    public ResponseEntity<String> signUp(@RequestBody User obj) {
        ProxySignUp proxy = new ProxySignUp(obj.getName(), obj.getId(), obj.getUserPassword(), this.userHandler);
        System.out.println(obj.getId());
        String email;
        try {
            email = proxy.addUser();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(email);
    }

    @GetMapping("signIn")
    public ResponseEntity<String> signIn(@RequestParam(value = "userEmail") String email, @RequestParam(value = "pass") String password) {
        System.out.println(password);
        ProxySignIn proxy = new ProxySignIn(email, password, this.userHandler);
        try {
            email = proxy.loadUser();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(email);
    }

    @PutMapping("signOut")
    public ResponseEntity<String> signOut(@RequestParam(value = "userEmail") String userEmail) {
        this.userCache.remove(userEmail);
        return ResponseEntity.status(HttpStatus.OK).body("Signed Out successfully!!");
    }

    @PutMapping("editContact")
    public ResponseEntity<String> editContact(@RequestParam("userEmail") String userEmail, @RequestBody Contact contact){
        System.out.println(contact.getName());
        if(contact.getUserEmails().contains(userEmail)){
            System.out.println("User editing himself");
            return ResponseEntity.status(HttpStatus.OK).body("User editing himself!!");
        }
        User user = this.userCache.retrieve(userEmail);
        user.editContact(contact);
        this.userCache.update(user);
        System.out.println("Contact for " + userEmail + "\t" + contact.getName() + " " + user.getContacts().get(contact.getName()));
        return ResponseEntity.status(HttpStatus.OK).body("Contact Edited Successfully!!");
    }

    @PostMapping("addContact")
    public ResponseEntity<String> addContact(@RequestParam("userEmail") String userEmail, @RequestBody Contact contact){
        System.out.println(contact.getName());
        if(contact.getUserEmails().contains(userEmail)){
            System.out.println("User adding himself");
            return ResponseEntity.status(HttpStatus.OK).body("User editing himself!!");
        }
        User user = this.userCache.retrieve(userEmail);
        for (String contactEmail : contact.getUserEmails()){
            user.addContact(contact.getName(), contactEmail);
        }
        this.userCache.update(user);
        System.out.println("Contact for " + userEmail + "\t" + contact.getName() + " " + user.getContacts().get(contact.getName()));
        return ResponseEntity.status(HttpStatus.OK).body("Contact Edited Successfully!!");
    }

    @DeleteMapping("deleteContact")
    public void deleteContact(@RequestParam("userEmail") String userEmail, @RequestParam("contactName") String contactName){
        User user = this.userCache.retrieve(userEmail);
        user.getContacts().remove(contactName);
        this.userCache.update(user);
    }

    @GetMapping("contacts")
    public ResponseEntity<Contact[]> getContactList(@RequestParam("userEmail") String userEmail)
    {
        User user = this.userCache.retrieve(userEmail);
        HashMap<String, Contact> userContacts = user.getContacts();

        return ResponseEntity.status(HttpStatus.OK).body(user.getContacts().values().toArray(new Contact[0]));
    }

    @GetMapping("contact")
    public ResponseEntity<Contact> getContact(@RequestParam("userEmail") String userEmail, @RequestParam("name") String contactName){
        User user = this.userCache.retrieve(userEmail);
        if(!user.getContacts().containsKey(contactName))
            return ResponseEntity.status(HttpStatus.OK).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(user.getContacts().get(contactName));
    }

    @PostMapping("newFolder")
    public ResponseEntity<String> createNewFolder(@RequestParam("userEmail") String userEmail, @RequestParam("name") String folderName){
        User user = this.userCache.retrieve(userEmail);
        if(user.getFolders().containsKey(folderName))
            return ResponseEntity.status(HttpStatus.OK).body("Folder already exists");
        System.out.println(folderName);
        user.addNewFolder(folderName);
        this.userCache.update(user);
        return ResponseEntity.status(HttpStatus.OK).body("Folder Created Successfully");
    }

    @GetMapping("folderList")
    public ResponseEntity<String[]> getFolderList(@RequestParam("userEmail") String userEmail){
        User user = this.userCache.retrieve(userEmail);
        String[] folderList = new String[user.getFolders().size()];
        int index = 0;
        for(Map.Entry<String, ArrayList<String>> folder : user.getFolders().entrySet())
            folderList[index++] = folder.getKey();
        return ResponseEntity.status(HttpStatus.OK).body(folderList);
    }

    @DeleteMapping("deleteFolder")
    public ResponseEntity<String> deleteFolder(@RequestParam("userEmail") String userEmail, @RequestParam("name") String folderName){
        User user = this.userCache.retrieve(userEmail);
        if(!user.getFolders().containsKey(folderName))
            return ResponseEntity.status(HttpStatus.OK).body("Folder doesn't exists");
        user.deleteFolder(folderName);
        this.userCache.update(user);
        return ResponseEntity.status(HttpStatus.OK).body("Folder Deleted Successfully");
    }

    @PutMapping("moveEmail")
    public ResponseEntity<String> moveEmail(@RequestParam("userEmail") String userEmail, @RequestParam("source") String sourceFolder, @RequestParam("destination") String destinationFolder, @RequestParam("emailId") String emailId){
        User user = this.userCache.retrieve(userEmail);
        if(!user.moveEmail(sourceFolder, destinationFolder, emailId))
            return ResponseEntity.status(HttpStatus.OK).body("Folder doesn't exists");
        this.userCache.update(user);
        return ResponseEntity.status(HttpStatus.OK).body("Email Moved Successfully");
    }
}
