package com.DEMOJWT.demo.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collation = "usuarios")
public class User {
    @Id
    private Long id;
    private String user;
    private String pwd;
    private String token;

    public User(){

    }
    public User(Long id, String user, String pwd, String token) {
        this.id = id;
        this.user = user;
        this.pwd = pwd;
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
