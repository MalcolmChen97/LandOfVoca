package com.tigerteam.landofvoca;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.util.List;

public class MultipleChoice extends AppCompatActivity {
    private Button[] CHOICE ;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);

        declareBtn();
        setUpbtn();
        update();


    }


    private void setUpbtn(){

        final Button next = (Button) findViewById(R.id.MultipleChoice_Next);
        next.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                update();
            }
        });

        int numOfChoice = getResources().getInteger(R.integer.num_Of_Choices);
        for(int i = 0;i < numOfChoice;i++){
            final Button btn = CHOICE[i];
            final int currentBtn = i;

            btn.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    if(checkCorrect()){
                        CHOICE[currentBtn].setBackgroundTintList(ColorStateList.valueOf(Color.argb(200,0,255,127)));
                        setWrongColor(currentBtn);
                    }
                    next.setVisibility(View.VISIBLE);
                }
            });
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void update(){
        final Button next = (Button) findViewById(R.id.MultipleChoice_Next);
        next.setVisibility(View.INVISIBLE);

        int numOfChoice = getResources().getInteger(R.integer.num_Of_Choices);
        for(int i = 0;i < numOfChoice; i++){
            CHOICE[i].setBackgroundTintList(ColorStateList.valueOf(Color.argb(200,220,220,220)));
        }

    }
    private boolean checkCorrect(){
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setWrongColor(int indexOfButton){
        int numOfChoice = getResources().getInteger(R.integer.num_Of_Choices);
        for(int i = 0; i < numOfChoice; i++){
            if(i != indexOfButton){
                CHOICE[i].setBackgroundTintList(ColorStateList.valueOf(Color.argb(255,255,64,64)));
            }
        }
    }
    private void declareBtn(){
        int numOfChoice = getResources().getInteger(R.integer.num_Of_Choices);
        CHOICE = new Button[numOfChoice];
        for(int i = 0 ; i < numOfChoice ; i++){
            int id = getResources().getIdentifier("MultipleChoice_" + i ,"id",getPackageName());
            CHOICE[i] = (Button) findViewById(id);
        }
    }
    public static Intent makeIntent(Context context){
        return new Intent(context,MultipleChoice.class);
    }

}
