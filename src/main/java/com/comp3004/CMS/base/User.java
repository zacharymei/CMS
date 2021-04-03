package com.comp3004.CMS.base;




import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class User {

    //@Id
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="userGen")
    //private long id;
    private String username;
    private String password;

//    @OneToOne
//    private Set<Long> studentUsers = new HashSet<Long>();


    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User() {

    }

//    public long getId() {
//        return id;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
