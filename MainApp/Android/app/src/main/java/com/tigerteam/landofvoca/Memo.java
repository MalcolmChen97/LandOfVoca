package com.tigerteam.landofvoca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Memo extends AppCompatActivity {
    Book book = Book.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        Button backbutton = (Button) findViewById(R.id.backtomain);
        backbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Memo.this,MainPage.class);
                startActivity(intent);
            }
        });

        ListView memoListView = (ListView) findViewById(R.id.mymemo);
        Map<String, String> items = new HashMap<>();
        List<Word> memo = book.getmemo();
        for(int i=0;i < memo.size();i++){
            items.put(memo.get(i).getEnglish(),memo.get(i).getChinese_meaning());
        }
        items.put("haha","qunima");
        List<HashMap<String,String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.list_items,
                new String[]{"First line","Second line"},new int[]{R.id.text1,R.id.text2});
        Iterator it = items.entrySet().iterator();
        while(it.hasNext()){
            HashMap<String,String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry) it.next();
            resultsMap.put("First line",pair.getKey().toString());
            resultsMap.put("Second line",pair.getValue().toString());
            listItems.add(resultsMap);
        }
        memoListView.setAdapter(adapter);
    }
}
