package com.tigerteam.landofvoca.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tigerteam.landofvoca.R;
import com.tigerteam.landofvoca.model.ProxyManager;
import com.tigerteam.landofvoca.model.User;
import com.tigerteam.landofvoca.proxy.ProxyBuilder;

public class LoginActivity extends AppCompatActivity {
    private static User user  = User.getInstance();

    private String userName = null;
    private String password = null;

    private ProgressBar progressBar;
    private Button loginBtn ;
    private TextView hereText ;
    private TextView registerText;
    private EditText userEmailText ;
    private EditText userPasswordText;


    private final static String savedUser = "saveUser";
    private final static String savedEmail = "savedEmail";
    private final static String savedPassword = "savedPassword";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ProxyManager.connectToServerWithoutToken(getApplicationContext());
        initializeUser();
        setUpAllBtn();
    }
    private void initializeUser(){
        getUser();
    }
    private void setUpAllBtn(){

        progressBar =  (ProgressBar) findViewById(R.id.Login_ProgressBar);
        loginBtn = (Button) findViewById(R.id.Login_Login);
        hereText = (TextView) findViewById(R.id.Login_Here);
        registerText = (TextView)findViewById(R.id.Login_Register);
        userEmailText = (EditText)findViewById(R.id.Login_Email);
        userPasswordText = (EditText)findViewById(R.id.Login_UserPassword);


        progressBar.setVisibility(View.INVISIBLE);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!user.getEmail().isEmpty()){
                    userEmailText.setText(user.getEmail());
                }
                if(!user.getPassword().isEmpty()){
                    userPasswordText.setText(user.getPassword());
                }
                if(userEmailText.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"You can't leave your Email empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(userPasswordText.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"You can't leave your Password empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                setAllInvisible();

                user.setEmail(userEmailText.getText().toString());
                user.setPassword(userPasswordText.getText().toString());
                ProxyBuilder.SimpleCallback<Boolean> callback = returnBoolean-> responseLogin(returnBoolean);
                ProxyManager.login(callback);
            }
        });

        hereText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RegisterActivity.makeIntent(getApplicationContext()));
            }
        });
    }


    public  void saveUser(){
        SharedPreferences pref = getSharedPreferences(savedUser,MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(savedEmail,user.getEmail());
        editor.putString(savedPassword,user.getPassword());
        editor.apply();
    }
    public void getUser(){
        SharedPreferences pref = getSharedPreferences(savedUser,MODE_PRIVATE);
        user.setEmail(pref.getString(savedEmail,null));
        user.setPassword(pref.getString(savedPassword,null));
    }


    private void setAllInvisible(){
        loginBtn.setVisibility(View.INVISIBLE);
        hereText.setVisibility(View.INVISIBLE);
        registerText.setVisibility(View.INVISIBLE);
        userEmailText.setVisibility(View.INVISIBLE);
        userPasswordText.setVisibility(View.INVISIBLE);
    }

    private void setAllVisible(){
        loginBtn.setVisibility(View.VISIBLE);
        hereText.setVisibility(View.VISIBLE);
        registerText.setVisibility(View.VISIBLE);
        userEmailText.setVisibility(View.VISIBLE);
        userPasswordText.setVisibility(View.VISIBLE);
    }


    //response
    private void responseLogin(Boolean bool){
        if(bool){
            MainPage.makeIntent(getApplicationContext());
            saveUser();
        }
        else{
            setAllVisible();
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
    private void timerForResponse(){
        Handler handler = new Handler();
        try {
            handler.wait(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public static Intent makeIntent(Context context){
        return  new Intent(context,LoginActivity.class);
    }


}
