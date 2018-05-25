package com.tigerteam.landofvoca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan Chen on 2018/5/17.
 */
//this is now the memo book!
public class Book {


    private List<Word> mymemo=new ArrayList<>();

    private static Book instance;
    public static Book getInstance(){
        if(instance == null){
            instance = new Book();
        }
        return instance;
    }

    public List<Word> getmemo(){
        return mymemo;
    }

    public static void addword(Word aword){
        getInstance().getmemo().add(aword);
    }

    public static void remove(Word aword){
        getInstance().getmemo().remove(aword);
    }



}
