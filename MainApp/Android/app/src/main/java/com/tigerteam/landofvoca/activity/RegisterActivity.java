package com.tigerteam.landofvoca.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tigerteam.landofvoca.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public static Intent makeIntent(Context context){
        return  new Intent(context,RegisterActivity.class);
    }
}
