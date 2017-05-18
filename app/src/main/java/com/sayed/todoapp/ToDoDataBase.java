package com.sayed.todoapp;

import android.app.Activity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.sayed.todoapp.TodoLoadListener.todoList;

/**
 * Created by Sayed on 5/19/2017.
 */
public class ToDoDataBase extends Activity {
    private static ToDoDataBase ourInstance = new ToDoDataBase();

    public static ToDoDataBase getInstance() {
        return ourInstance;
    }

    private ToDoDataBase() {
    }
    void loadTodoList(TodoLoadListener listener) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference("todoList").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        todoList.clear();
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            ToDo toDo = data.getValue(ToDo.class);
                            todoList.add(toDo);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

        if (listener != null)
            listener.onDataLoaded(todoList);
    }
}
