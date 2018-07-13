package com.tigerteam.landofvoca.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tigerteam.landofvoca.R;
import com.tigerteam.landofvoca.model.User;

public class LoginActivity extends AppCompatActivity {
    private static User user  = User.getInstance();
    private String userName = null;
    private String password = null;

    private ProgressBar progressBar;
    private Button loginBtn ;
    private TextView hereText ;
    private TextView registerText;
    private EditText userNameText ;
    private EditText userPasswordText;


    private final static String savedUser = "saveUser";
    private final static String savedUserName = "savedUserName";
    private final static String savedPassword = "savedPassword";
    private final static String savedIsLogin = "savedIsLogin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
        userNameText = (EditText)findViewById(R.id.Login_UserName);
        userPasswordText = (EditText)findViewById(R.id.Login_UserPassword);


        progressBar.setVisibility(View.INVISIBLE);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllInvisible();
                loginTask login = new loginTask();
                login.execute();
            }
        });

        hereText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RegisterActivity.makeIntent(getApplicationContext()));
            }
        });
    }
    public class loginTask extends AsyncTask <Void,Void,Boolean> {
        @Override
        protected void onPreExecute() {
            ProgressBar progressBar = findViewById(R.id.Login_ProgressBar);
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected Boolean doInBackground(Void... x) {
            try {
                // Simulate network access.
                //TODO: internet authorization accessThread
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                return false;
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                startActivity(MainPage.makeIntent(LoginActivity.this));
                saveUser();
                finish();
            }
            else{
                progressBar.setVisibility(View.INVISIBLE);
                setAllVisible();
            }
        }
    }

    public  void saveUser(){
        SharedPreferences pref = getSharedPreferences(savedUser,MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(savedUserName,user.getUserName());
        editor.putString(savedPassword,user.getPassword());
        editor.putBoolean(savedIsLogin,user.getLogin());
        editor.apply();
    }
    public void getUser(){
        SharedPreferences pref = getSharedPreferences(savedUser,MODE_PRIVATE);
        user.setUserName(pref.getString(savedUserName,null));
        user.setPassword(pref.getString(savedPassword,null));
        user.setLogin(pref.getBoolean(savedIsLogin,false));
    }


    private void setAllInvisible(){
        loginBtn.setVisibility(View.INVISIBLE);
        hereText.setVisibility(View.INVISIBLE);
        registerText.setVisibility(View.INVISIBLE);
        userNameText.setVisibility(View.INVISIBLE);
        userPasswordText.setVisibility(View.INVISIBLE);
    }

    private void setAllVisible(){
        loginBtn.setVisibility(View.VISIBLE);
        hereText.setVisibility(View.VISIBLE);
        registerText.setVisibility(View.VISIBLE);
        userNameText.setVisibility(View.VISIBLE);
        userPasswordText.setVisibility(View.VISIBLE);
    }

    public static Intent makeIntent(Context context){
        return  new Intent(context,LoginActivity.class);
    }
}
