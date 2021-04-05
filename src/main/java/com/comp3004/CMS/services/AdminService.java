package com.comp3004.CMS.services;

import com.comp3004.CMS.base.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private Admin admin;

    public boolean getLoggedin(){
        return admin.getLoggedin();
    }

    public boolean logout(){
        admin.logout();
        return true;
    }

    public boolean login(String username, String password) {
        if (admin.getUsername().equals(username)) {
            if (admin.getPassword().equals(password)) {
                admin.login();
                return true;
            }
        }
        return false;
    }



}
