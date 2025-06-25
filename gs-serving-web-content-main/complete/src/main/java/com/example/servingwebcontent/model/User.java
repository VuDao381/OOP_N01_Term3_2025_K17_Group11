package com.example.servingwebcontent.model;

import jakarta.persistence.*;

@Entity
@Table(name = "library_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, length = 30)
    private String username;

    @Column(name = "useremail", nullable = false, length = 50, unique = true)
    private String useremail;

    @Column(name = "userpassword", nullable = false, length = 100)
    private String userpassword;

    public User() {}

    public User(String username, String useremail, String userpassword) {
        this.username = username;
        this.useremail = useremail;
        this.userpassword = userpassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getUseremail() {
        return useremail;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }
}
