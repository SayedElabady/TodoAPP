package com.sayed.todoapp;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Sayed on 5/18/2017.
 */
public class ToDo implements Serializable {

    private String name;
    private String message;
    private String date;
    private String uid;

    public ToDo() {

    }
    public String getDate() {
        return date;
    }
    public String getUid() {
        return uid;
    }


    public void setDate(String date) {
        this.date = date;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public HashMap<String,String> toFirebaseObject() {
        HashMap<String,String> todo =  new HashMap<String,String>();
        todo.put("name", name);
        todo.put("message", message);
        todo.put("date", date);

        return todo;
    }

}