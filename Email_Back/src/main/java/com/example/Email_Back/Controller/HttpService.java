package com.example.Email_Back.Controller;

import com.example.Email_Back.Model.SignIn.ProxySignIn;
import com.example.Email_Back.Model.SignUp.ProxySignUp;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/")
public class HttpService {

    @PostMapping("signUp")
    public String signUp(@RequestBody ProxySignUp obj) {
        try {
            obj.addUser();
        } catch (Exception e) {
            return e.getMessage();
        }
        return "User saved successfully";
    }

    @PostMapping("signIn")
    public User signIn(@RequestBody ProxySignIn obj) {
        User user;
        try {
            user = obj.loadUser();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return user;
    }

}
