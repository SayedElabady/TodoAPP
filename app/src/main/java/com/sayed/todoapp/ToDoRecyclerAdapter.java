package com.sayed.todoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


    public class ToDoRecyclerAdapter extends RecyclerView.Adapter<ToDoRecyclerAdapter.SimpleItemViewHolder> {
    ArrayList<ToDo> todoList;
        RecyclerView recyclerView;
        public ToDoRecyclerAdapter(){
            todoList = new ArrayList<>();
        }

        void setList(ArrayList<ToDo> List){
            todoList = List;
        }
        ArrayList<ToDo> getList(){
            return todoList;
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
        ToDo todo = todoList.get(position);
        (holder).title.setText(todo.getName());
        holder.v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int itemPosition = ToDoActivity.recyclerView.getChildLayoutPosition(v);
                final ToDo item = todoList.get(itemPosition);
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(v.getContext(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(v.getContext());
                }
                builder.setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                DatabaseReference database = FirebaseDatabase.getInstance().getReference("todoList").child(uid);

                                database.child(item.getName()).removeValue();
                                //database.child(item.getDate()).removeValue();
                               // database.child(item.getDate()).removeValue();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return true;
            }
        });
    }



        public final class SimpleItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView title;
            public int position;
            public View v;

            public SimpleItemViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                this.v = itemView;
                title = (TextView) itemView.findViewById(R.id.myTextView);
            }

            @Override
            public void onClick(final View view) {

               }
        }
    }

