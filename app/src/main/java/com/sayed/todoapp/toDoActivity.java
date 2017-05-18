package com.sayed.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class toDoActivity extends AppCompatActivity implements TodoLoadListener{
    ToDoRecyclerAdapter adapter;
    ArrayList<ToDo> todoList;
    TodoLoadListener todoLoadListener;
    ToDoDataBase toDoDataBase;




    @Override
    protected void onStart() {
        super.onStart();
       toDoDataBase =  ToDoDataBase.getInstance();
        toDoDataBase.loadTodoList(todoLoadListener);
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
        ButterKnife.bind(this);
        adapter.notifyDataSetChanged();
    }
    @OnClick(R.id.fab)
    public void enterAddActivity(View view) {
        Intent intent = new Intent(toDoActivity.this, ToDoAddActivity.class);
        startActivity(intent);


    }


    @Override
    public void onDataLoaded(ArrayList<ToDo> dataList) {
        adapter.setList(todoList);
        adapter.notifyDataSetChanged();
    }
}