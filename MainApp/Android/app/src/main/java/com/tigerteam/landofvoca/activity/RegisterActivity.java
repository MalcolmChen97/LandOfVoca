package com.tigerteam.landofvoca.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tigerteam.landofvoca.R;
import com.tigerteam.landofvoca.model.ProxyManager;
import com.tigerteam.landofvoca.model.User;
import com.tigerteam.landofvoca.proxy.ProxyBuilder;

public class RegisterActivity extends AppCompatActivity {
    private User user = User.getInstance();
    private Button register;
    private EditText email;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setUpBtn();
    }

    private void setUpBtn(){
        register = (Button) findViewById(R.id.Register_Register);
        email = (EditText) findViewById(R.id.Register_Email);
        password =(EditText) findViewById(R.id.Register_Password);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"You can't leave your Email empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"You can't leave your Password empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                ProxyManager.connectToServerWithoutToken(getApplicationContext());
                ProxyBuilder.SimpleCallback<Boolean> callback = returnBoolean->responseForRegister(returnBoolean);
                user.setEmail(email.getText().toString());
                user.setPassword(password.getText().toString());
                ProxyManager.register(callback);
            }
        });
    }
    private void responseForRegister(Boolean bool){
            finish();
    }
    public static Intent makeIntent(Context context){
        return  new Intent(context,RegisterActivity.class);
    }
}
