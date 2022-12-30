package com.example.Email_Back.Controller;

import com.example.Email_Back.Model.Caches.UserCache;
import com.example.Email_Back.Model.User.SignIn.ProxySignIn;
import com.example.Email_Back.Model.User.SignUp.ProxySignUp;
import com.example.Email_Back.Model.User.User;
import com.example.Email_Back.Model.User.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
