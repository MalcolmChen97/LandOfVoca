package com.tigerteam.landofvoca.model;

/**
 * Created by Jonathan Chen on 2018/7/12.
 */

public class User {


    private int id;
    private String email;
    private String username;
    private String password;
    private String nickname;
    private String token;

    private static User instance;

    private User(){
        this.email = null;
        this.password = null;
    }

    public static User getInstance(){
        if(instance == null){
            instance  = new User();
        }
        return  instance;
    }

    //getter
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    //setter
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


}
