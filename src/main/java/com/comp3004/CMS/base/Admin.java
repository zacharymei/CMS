package com.comp3004.CMS.base;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Component
public class Admin {

    private static String username = "admin";
    private static  String password = "admin";
    private static boolean loggedin = false;

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setUsername(String username) {
        Admin.username = username;
    }

    public static void setPassword(String password) {
        Admin.password = password;
    }

    public boolean getLoggedin(){
        return Admin.loggedin;
    }

    public void login(){
        Admin.loggedin = true;
    }

    public void logout(){
        Admin.loggedin = false;
    }
}
