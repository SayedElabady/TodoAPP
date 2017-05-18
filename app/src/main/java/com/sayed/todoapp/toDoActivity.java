package com.sayed.todoapp;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class toDoActivity extends AppCompatActivity {
    ToDoRecyclerAdapter adapter;
    ArrayList<todo> todoList;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ButterKnife.bind(this);
    }

    public void retrieveDataFromDB(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference("todoList").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        todoList.clear();
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            todo toDo = data.getValue(todo.class);
                            todoList.add(toDo);
                        }
                        adapter.todoList = todoList;
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();
        retrieveDataFromDB();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        todoList = new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        adapter = new ToDoRecyclerAdapter();
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }
    @OnClick(R.id.fab)
    public void enterAddActivity(View view) {
        Intent intent = new Intent(toDoActivity.this, ToDoAddActivity.class);
        startActivity(intent);


    }



}