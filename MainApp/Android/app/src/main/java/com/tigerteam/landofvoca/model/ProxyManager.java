package com.tigerteam.landofvoca.model;

import android.content.Context;

import android.util.Log;

import com.tigerteam.landofvoca.proxy.ApiInterface;
import com.tigerteam.landofvoca.proxy.ProxyBuilder;

import retrofit2.Call;

/**
 * Created by Jonathan Chen on 2018/7/12.
 */

public class ProxyManager {
    private static ApiInterface  proxy;
    private static Context currentContext;
    private static User user = User.getInstance();



    //connection
    public static void connectToServerWithoutToken(Context context){
        currentContext = context;
        proxy = ProxyBuilder.getProxy(null);
    }
    public static void connectToServerWithToken(Context context){
        currentContext = context;
        proxy = ProxyBuilder.getProxy(user.getToken());
    }


    //refresh token
    public static void refreshToken(){
        ProxyBuilder.setOnTokenReceiveCallback(token->onReceiveToken(token));
    }

    private static void onReceiveToken(String token) {
        // Replace the current proxy with one that uses the token!
        String TAG = "Proxy";
        Log.w(TAG, "   --> NOW HAVE TOKEN: " + token);
        user.setToken(token);
        proxy = ProxyBuilder.getProxy(user.getToken());
    }
    //test getLogin
    public static void getToLogin(ProxyBuilder.SimpleCallback<User> callback){
        Call<User> callerForGetLogin = proxy.getToLogin();
        ProxyBuilder.callProxy(currentContext,callerForGetLogin,callback);
    }

    //Login
    public static void login(ProxyBuilder.SimpleCallback<Boolean> callback){
        Call<Boolean> callerForLogin = proxy.login(user);
        ProxyBuilder.callProxy(currentContext,callerForLogin,callback);
    }
    //register
    public static void register(ProxyBuilder.SimpleCallback<Boolean> callback){
        Call<Boolean> callerForRegister = proxy.register(user);
        ProxyBuilder.callProxy(currentContext,callerForRegister,callback);
    }

}
