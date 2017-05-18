package com.sayed.todoapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


    public class ToDoRecyclerAdapter extends RecyclerView.Adapter<ToDoRecyclerAdapter.SimpleItemViewHolder> {
    ArrayList<todo> todoList;
        public ToDoRecyclerAdapter(){
            todoList = new ArrayList<>();
        }

        @Override
        public int getItemCount() {
            return todoList.size();
        }

        @Override
        public ToDoRecyclerAdapter.SimpleItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
            SimpleItemViewHolder pvh = new SimpleItemViewHolder(v);
            return pvh;
        }

    @Override
    public void onBindViewHolder(SimpleItemViewHolder holder, int position) {
        holder.position = position;
        todo todo = todoList.get(position);
        (holder).title.setText(todo.getName());
    }



        public final class SimpleItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView title;
            public int position;

            public SimpleItemViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                title = (TextView) itemView.findViewById(R.id.myTextView);
            }

            @Override
            public void onClick(View view) {

            }
        }
    }

