package com.example.Email_Back.Controller;

import com.example.Email_Back.Model.User.SignIn.ProxySignIn;
import com.example.Email_Back.Model.User.SignUp.ProxySignUp;
import com.example.Email_Back.Model.User.User;
import com.example.Email_Back.Model.User.UserCahce;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/user/")
public class UserService {

    private UserCahce cache = new UserCahce();

//    public UserService(UserCahce cahce) {
//        this.cache = cahce;
//    }

    @PostMapping("signUp")
    public String signUp(@RequestBody User obj) {
        ProxySignUp proxy = new ProxySignUp(obj.getName(), obj.getUserEmail(), obj.getUserPassword(), this.cache);
        try {
            proxy.addUser();
        } catch (Exception e) {
            return e.getMessage();
        }
        return "User saved successfully";
    }

    @GetMapping("signIn")
    public User signIn(@RequestBody User obj) {
        ProxySignIn proxy = new ProxySignIn(obj.getUserEmail(), obj.getUserPassword(), this.cache);
        User user;
        try {
            user = proxy.loadUser();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return user;
    }

}
