package com.simple.controller;

import com.simple.model.Users;
import com.simple.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(NewUserSystem.DIR)
public class NewUserSystem {

    @Autowired
    UsersRepository usersRepository;

    public static final String DIR = "/";
    public static final String NEWUSER = "/newuser";
    public static final String ADDUSER = "/adduser";


    @GetMapping(value = DIR)
    public String indexMethod(Model model){
        model.addAttribute("mensaje","hola");
        return "index";
    }

    @GetMapping(value = NEWUSER)
    public String newUserMethod(){
        return "formuser";
    }
    @PostMapping(value = ADDUSER) // Map ONLY POST Requests
    public @ResponseBody Users addNewUser (@RequestParam String name, @RequestParam String password) {
        Users users = new Users();
        users.setName(name);
        users.setPassword(password);
        return  usersRepository.save(users);
    }

}
