package com.tigerteam.landofvoca;

import java.util.List;

/**
 * Created by Jonathan Chen on 2018/5/17.
 */

public class Book {


    private List<String> english;
    private List<String> chinese;
    private int size;

    private static Book instance;
    public static Book getInstance(){
        if(instance == null){
            instance = new Book();
        }
        return instance;
    }



    public List<String> getEnglish() {
        return english;
    }

    public void setEnglish(List<String> english) {
        this.english = english;
    }

    public List<String> getChinese() {
        return chinese;
    }

    public void setChinese(List<String> chinese) {
        this.chinese = chinese;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
