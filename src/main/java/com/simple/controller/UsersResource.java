package com.simple.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("authenticated")
@RestController
@RequestMapping(UsersResource.ROOT)
public class UsersResource {
    public static final String ROOT = "/security";
    public static final String ALLDIR = "/all";
    public static final String USERDIR = "/user";
    public static final String MANAGERDIR = "/manager";
    public static final String ADMINDIR = "/admin";

    @GetMapping(value = ALLDIR)
    public String all(){
        return "acceso permitido";
    }

    @PreAuthorize("hasRole('USER') OR hasRole('MANGER')")
    @GetMapping(value = USERDIR)
    public String userMethod(){
        return "acceso,rol user";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping(value = MANAGERDIR)
    public String managerMethod(){
        return "acceso, rol manager";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = ADMINDIR)
    public String adminMethod(){
        return "acceso, rol admin";
    }


}
