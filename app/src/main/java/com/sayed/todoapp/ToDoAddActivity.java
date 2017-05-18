package com.sayed.todoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class ToDoAddActivity extends AppCompatActivity {
    EditText name , message ;
    DatePicker datepicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_edit);
        name = (EditText) findViewById(R.id.nameEditText);
        message = (EditText) findViewById(R.id.messageEditText);
        datepicker = (DatePicker) findViewById(R.id.datepicker);
    }

    public void savetoDatabase(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String key = database.getReference("todoList").push().getKey();
        String nameStr = name.getText().toString();
        String messageStr = message.getText().toString();
        Date date = new Date();
        date.setMonth(datepicker.getMonth());
        date.setYear(datepicker.getYear());
        date.setDate(datepicker.getDayOfMonth());

        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy HH:MM");

        String dateStr = format.format(date);
        database.getReference("todoList").child("name").push().setValue(nameStr);
        database.getReference("todoList").child("message").push().setValue(messageStr);
        database.getReference("todoList").child("date").push().setValue(dateStr);





        todo toDo = new todo();
        toDo.setDate(dateStr);
        toDo.setName(nameStr);
        toDo.setMessage(messageStr);

        HashMap<String , Object> toDoChild = new HashMap<>();
        toDoChild.put(key , toDo);
        database.getReference("todoList").updateChildren(toDoChild, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    finish();
                }
            }
        });


    }
}
