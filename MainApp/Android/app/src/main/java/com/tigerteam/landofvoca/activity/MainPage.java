package com.tigerteam.landofvoca.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.tigerteam.landofvoca.R;
import com.tigerteam.landofvoca.model.Book;
import com.tigerteam.landofvoca.model.User;
import com.tigerteam.landofvoca.model.Word;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static User user = User.getInstance();

    private Book book = Book.getInstance();
    private String bookname;
    private List<Word> mybook= new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        setUpspinner();
        setUpbtn();

    }


    private void setUpspinner(){
        Spinner bookchooser = (Spinner) findViewById(R.id.bookchooser);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.books,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bookchooser.setAdapter(adapter);
        bookchooser.setOnItemSelectedListener(this);
    }

    private void setUpbtn(){
        final Button start = (Button)findViewById(R.id.MainPage_Start);
        Button entermemo = (Button) findViewById(R.id.memobut);
        entermemo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this,Memo.class);
                startActivity(intent);
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MultipleChoice.makeIntent(MainPage.this);
                intent.putExtra("book",bookname);
                startActivity(intent);
            }
        });
    }




    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        bookname = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(adapterView.getContext(),"you need to choose a book to start",Toast.LENGTH_SHORT);
    }

    public static Intent makeIntent(Context context){
        return new Intent(context,MainPage.class);
    }
}
