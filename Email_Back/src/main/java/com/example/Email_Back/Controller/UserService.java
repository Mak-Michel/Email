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
        String email;
        try {
            email = proxy.addUser();
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(email, HttpStatus.OK);
    }

    @GetMapping("signIn")
    public ResponseEntity<String> signIn(@RequestBody User obj) {
        ProxySignIn proxy = new ProxySignIn(obj.getUserEmail(), obj.getUserPassword(), this.userHandler);
        String email;
        try {
            email = proxy.loadUser();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(email, HttpStatus.OK);
    }

}
