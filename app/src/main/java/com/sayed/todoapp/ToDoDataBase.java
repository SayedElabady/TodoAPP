package com.sayed.todoapp;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Sayed on 5/19/2017.
 */
public class ToDoDataBase extends Activity {
    private static ToDoDataBase ourInstance = new ToDoDataBase();
    ArrayList<ToDo> todoList = new ArrayList<>();
    public static ToDoDataBase getInstance() {
        return ourInstance;
    }

    private ToDoDataBase() {
    }
    void loadTodoList(final TodoLoadListener listener) {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("todoList").child(uid);

        database.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        todoList.clear();
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            ToDo toDo = data.getValue(ToDo.class);
                            todoList.add(toDo);
                        }
                        if (listener != null)
                            listener.onDataLoaded(todoList);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });


    }

}
