package com.sayed.todoapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sayed on 5/19/2017.
 */

public interface TodoLoadListener {
    ArrayList<ToDo> todoList = new ArrayList<>() ;

    void onDataLoaded(ArrayList<ToDo> dataList);
}
