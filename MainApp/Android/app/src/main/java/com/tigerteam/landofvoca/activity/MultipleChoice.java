package com.tigerteam.landofvoca.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tigerteam.landofvoca.R;
import com.tigerteam.landofvoca.model.Book;
import com.tigerteam.landofvoca.model.Word;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultipleChoice extends AppCompatActivity {
    private Button[] CHOICE ;
    private int numOfChoice;
    private Book book = Book.getInstance();
    private String problem;
    private String answer;
    private String bookname;
    private List<Word> mybook= new ArrayList<>();
    private Word current_word;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);
        Intent in= getIntent();
        bookname = in.getStringExtra("book");
        initilizeBook();

        declareBtn();
        setUpbtn();
        update();


    }

    private void initilizeBook(){
        int id;
        switch (bookname){
            case "toefl":
                id = R.raw.toefl;
                break;
            case "gre":
                id = R.raw.gre;
                break;
            default:
                id = R.raw.test;
                break;
        }
        InputStream is = getResources().openRawResource(id);
        BufferedReader reader = new BufferedReader((new InputStreamReader(is, Charset.forName("UTF-8"))));
        String line = "";
        try{
            while((line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                Word newword = new Word();
                newword.setEnglish(tokens[0]);
                newword.setChinese_meaning(tokens[1]);
                mybook.add(newword);
                Log.d("word",newword.getEnglish());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void declareBtn(){
        numOfChoice = getResources().getInteger(R.integer.num_Of_Choices);
        CHOICE = new Button[numOfChoice];
        for(int i = 0 ; i < numOfChoice ; i++){
            int id = getResources().getIdentifier("MultipleChoice_" + i ,"id",getPackageName());
            CHOICE[i] = (Button) findViewById(id);
        }
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


        for(int i = 0;i < numOfChoice;i++){
            final Button btn = CHOICE[i];
            final int currentBtn = i;

            btn.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    if(checkCorrect(currentBtn)){
                        CHOICE[currentBtn].setBackgroundTintList(ColorStateList.valueOf(Color.argb(200,0,255,127)));
                        setWrongColor(currentBtn);
                        goodJobAnnounce();
                    }
                    else{
                        showResult();
                        niceTryAnnounce();
                        Book.addword(current_word);
                        Log.i("add","dsa");
                    }
                    next.setVisibility(View.VISIBLE);
                }
            });
        }


    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void update(){
        //set next invisible
        final Button next = (Button) findViewById(R.id.MultipleChoice_Next);
        next.setVisibility(View.INVISIBLE);

        //refresh button
        for(int i = 0;i < numOfChoice; i++){
            CHOICE[i].setBackgroundTintList(ColorStateList.valueOf(Color.argb(200,220,220,220)));
            CHOICE[i].setText("Unknown error");
        }

        //select random voca
        Random rand = new Random();
        int randomVoca = rand.nextInt(mybook.size());

        problem = mybook.get(randomVoca).getEnglish();
        answer = mybook.get(randomVoca).getChinese_meaning();
        current_word = mybook.get(randomVoca);
        //set question
        TextView question = (TextView)findViewById(R.id.MultipleChoice_Question);
        question.setText(problem);

        //set rand pos
        int randomIndex = rand.nextInt(numOfChoice-1);
        CHOICE[randomIndex].setText(answer);
        setWrongAnswer(randomIndex);

    }

    private void setWrongAnswer(int answerIndex){

        for(int i = 0; i < numOfChoice;i++ ){
            boolean find = false;

            while(!find && answerIndex != i){

                Random rand = new Random();
                int randomVoca = rand.nextInt(mybook.size());

                if(checkNoSame(mybook.get(randomVoca).getChinese_meaning())){
                    find = true;
                    CHOICE[i].setText(mybook.get(randomVoca).getChinese_meaning());
                }
            }
        }
    }
    private boolean checkNoSame(String choice){
        for(int i = 0; i < numOfChoice;i++){
            if (CHOICE[i].getText().equals(choice))
                return false;
        }
        return true;
    }
    private boolean checkCorrect(int i){
        if(CHOICE[i].getText().equals(answer))
           return true;
        return false;
    }
    private void goodJobAnnounce(){
        Toast.makeText(this,"Good job",Toast.LENGTH_SHORT).show();
    }
    private void niceTryAnnounce(){
        Toast.makeText(this,"Nice try",Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setWrongColor(int indexOfButton){
        for(int i = 0; i < numOfChoice; i++){
            if(i != indexOfButton){
                CHOICE[i].setBackgroundTintList(ColorStateList.valueOf(Color.argb(255,255,64,64)));
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void showResult(){
        
        for(int i = 0; i < numOfChoice; i++){
            if(CHOICE[i].getText().equals(answer))
                CHOICE[i].setBackgroundTintList(ColorStateList.valueOf(Color.argb(255,255,64,64)));

            else
                CHOICE[i].setBackgroundTintList(ColorStateList.valueOf(Color.argb(200,0,255,127)));
        }
    }


    public static Intent makeIntent(Context context){
        return new Intent(context,MultipleChoice.class);
    }

}
