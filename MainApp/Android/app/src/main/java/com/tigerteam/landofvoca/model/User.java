package com.tigerteam.landofvoca.model;

/**
 * Created by Jonathan Chen on 2018/7/12.
 */

public class User {



    private String userName;
    private String password;
    private boolean isLogin;
    private static User instance;

    private User(){
        this.userName = null;
        this.password = null;
        this.isLogin = false;
    }

    public static User getInstance(){
        if(instance == null){
            instance  = new User();
        }
        return  instance;
    }

    //getter
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean getLogin() {
        return isLogin;
    }

    //setter
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }


}
