package be.webshop.controller;

import be.webshop.api.UserTo;
import be.webshop.exception.UsernameBestaatAl;
import be.webshop.user.User;
import be.webshop.user.UserFactory;
import be.webshop.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import static be.webshop.user.Role.CUSTOMER;

@RestController
@Transactional
public class UserController {
    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/sign-up")
    @RequestMapping(produces = "application/json")
    public void signUp(@RequestBody UserTo user) throws UsernameBestaatAl {
        User realUser = UserFactory.assembleUser(user);
        realUser.setPassword(bCryptPasswordEncoder.encode(realUser.getPassword()));
        realUser.setRole(CUSTOMER);
        userService.store(realUser);
    }

    @PostMapping("/login")
    public void login() {
    }
}
