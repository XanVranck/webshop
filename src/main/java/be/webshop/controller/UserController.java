package be.webshop.controller;

import be.webshop.user.User;
import be.webshop.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@Transactional
public class UserController {
    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/sign-up")
    @RequestMapping(produces = "application/json")
    public void signUp(@RequestParam String username,
                       @RequestParam String password) {
        User realUser = new User(username, bCryptPasswordEncoder.encode(password));
        userService.store(realUser);
    }

    @PostMapping("/login")
    public void login() {
        System.out.println("loggin in");
    }
}
