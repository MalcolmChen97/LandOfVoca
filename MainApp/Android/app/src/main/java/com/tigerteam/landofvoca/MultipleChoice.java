package com.tigerteam.landofvoca;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.util.List;

public class MultipleChoice extends AppCompatActivity {
    private Button[] CHOICE ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);

        declareBtn();
        setUpbtn();


    }


    private void setUpbtn(){
        int numOfChoice = getResources().getInteger(R.integer.num_Of_Choices);
        for(int i = 0;i < numOfChoice;i++){
            final Button btn = CHOICE[i];
            final int currentBtn = i;

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkCorrect()){
                        CHOICE[currentBtn].setBackgroundColor(Color.GREEN);
                        setWrongColor(currentBtn);
                    }
                }
            });
        }
    }
    private boolean checkCorrect(){
        return true;
    }
    private void setWrongColor(int indexOfButton){
        int numOfChoice = getResources().getInteger(R.integer.num_Of_Choices);
        for(int i = 0; i < numOfChoice; i++){
            if(i != indexOfButton){
                CHOICE[i].setBackgroundColor(Color.RED);
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
