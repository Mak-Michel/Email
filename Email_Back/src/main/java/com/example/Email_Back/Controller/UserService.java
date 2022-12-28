package com.example.Email_Back.Controller;

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

    @PostMapping("signUp")
    public ResponseEntity<String> signUp(@RequestBody User obj) {
        ProxySignUp proxy = new ProxySignUp(obj.getName(), obj.getUserEmail(), obj.getUserPassword(), this.userHandler);
        try {
            proxy.addUser();
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("User saved successfully", HttpStatus.OK);
    }

    @GetMapping("signIn")
    public ResponseEntity<User> signIn(@RequestBody User obj) {
        ProxySignIn proxy = new ProxySignIn(obj.getUserEmail(), obj.getUserPassword(), this.userHandler);
        User user;
        try {
            user = proxy.loadUser();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

}
