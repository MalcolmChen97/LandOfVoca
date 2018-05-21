package com.tigerteam.landofvoca;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Book book = Book.getInstance();
    private String bookname;
    private List<Word> mybook= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        setUpspinner();
        setUpbtn();
        //initilizeBook();
    }


    private void setUpspinner(){
        Spinner bookchooser = (Spinner) findViewById(R.id.bookchooser);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.books,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bookchooser.setAdapter(adapter);
        bookchooser.setOnItemSelectedListener(this);
    }

    private void setUpbtn(){
        Button start = (Button)findViewById(R.id.MainPage_Start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MultipleChoice.makeIntent(MainPage.this);
                intent.putExtra("book",bookname);
                startActivity(intent);
            }
        });
    }
//    private void initilizeBook(){
//        book.setSize(getVocaNum(this,"test"));
//        book.setEnglish(getPreferenceForVocabularyBook(this,"testeng",book.getSize()));
//        book.setChinese(getPreferenceForVocabularyBook(this,"testchi",book.getSize()));
//        Toast.makeText(this,book.getChinese().get(0)+book.getChinese().get(1)+book.getChinese().get(2)+book.getChinese().get(3),Toast.LENGTH_LONG).show();
//    }
//    private void initilizeBook(){
//        List<String> eng = new ArrayList<>();
//        eng.add("apple");
//        eng.add("banana");
//        eng.add("nut");
//        eng.add("coconut");
//
//        List<String> chi = new ArrayList<>();
//        chi.add("苹果");
//        chi.add("香蕉");
//        chi.add("花生");
//        chi.add("椰子");
//
//        saveVocaNum(this,"test",4);
//        savePreferenceForVocabularyBook(this,"testeng",eng);
//        savePreferenceForVocabularyBook(this,"testchi",chi);
//    }


    public static void saveVocaNum(Context context,String book, int size){
        SharedPreferences pref = context.getSharedPreferences(book,MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("size",size);
        editor.apply();
    }

    public static int getVocaNum(Context context, String book){
        SharedPreferences pref = context.getSharedPreferences(book,MODE_PRIVATE);
        return  pref.getInt("size",-1);

    }

    public static void savePreferenceForVocabularyBook(Context context, String bookAndLanguage, List<String> base){
        SharedPreferences pref = context.getSharedPreferences(bookAndLanguage,MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        for (int i = 0; i < base.size();i++){
            editor.putString(bookAndLanguage+i,base.get(i));
        }
        editor.apply();
    }

    public static List<String> getPreferenceForVocabularyBook(Context context,String bookAndLanguage,int size){
        SharedPreferences pref = context.getSharedPreferences(bookAndLanguage,MODE_PRIVATE);
        List<String> base = new ArrayList<>();
        for(int i = 0;i < size ;i++){
            base.add(pref.getString(bookAndLanguage+i,""));
        }
        return base;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        bookname = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(adapterView.getContext(),"you need to choose a book to start",Toast.LENGTH_SHORT);
    }
}
