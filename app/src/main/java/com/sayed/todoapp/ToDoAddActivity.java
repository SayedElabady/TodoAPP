package com.sayed.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToDoAddActivity extends AppCompatActivity{
    @BindView(R.id.datepicker) DatePicker datepicker;
    @BindView(R.id.messageEditText) EditText message;
    @BindView(R.id.nameEditText) EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_edit);
        ButterKnife.bind(this);

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
        //database.getReference("todoList").child("name").push().setValue(nameStr);
        // database.getReference("todoList").child("message").push().setValue(messageStr);
        // database.getReference("todoList").child("date").push().setValue(dateStr);





        ToDo toDo = new ToDo();
        toDo.setDate(dateStr);
        toDo.setName(nameStr);
        toDo.setMessage(messageStr);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        HashMap<String , Object> toDoChild = new HashMap<>();
        toDoChild.put(key , toDo);
        database.getReference("todoList").child(uid).push().setValue(toDo);
        Intent intent = new Intent(ToDoAddActivity.this , ToDoActivity.class);
        startActivity(intent);



    }




}
