package com.sayed.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToDoActivity extends AppCompatActivity implements TodoLoadListener{
    ToDoRecyclerAdapter adapter;
    ToDoDataBase toDoDataBase;
    public static RecyclerView recyclerView;

   // @BindView(R.id.recyclerView) RecyclerView recyclerView;

    @Override
    protected void onStart() {
        super.onStart();
        toDoDataBase.loadTodoList(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ButterKnife.bind(this);

        //bind the adapter to the recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        adapter = new ToDoRecyclerAdapter();
        recyclerView.setAdapter(adapter);
        // getting the instance of the db
        toDoDataBase =  ToDoDataBase.getInstance();
    }

    // floating action button handler .. " get into the add activity "
    @OnClick(R.id.fab)
    public void enterAddActivity(View view) {
        Intent intent = new Intent(ToDoActivity.this, ToDoAddActivity.class);
        startActivity(intent);
    }

    // implmenting the interface funstion .. bind data after loading it from db
    @Override
    public void onDataLoaded(ArrayList<ToDo> dataList) {
        adapter.setList(dataList);
        adapter.notifyDataSetChanged();
    }


}