package adapter;

import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sayed.todoapp.R;

import listener.TodoLoadListener;
import listener.onItemInteractionListener;
import model.ToDo;

import java.util.ArrayList;




public class ToDoRecyclerAdapter extends RecyclerView.Adapter<ToDoRecyclerAdapter.SimpleItemViewHolder> {
    ArrayList<ToDo> todoList;
    TodoLoadListener todoLoadListener;
    onItemInteractionListener onItemInteractionListener;
        public ToDoRecyclerAdapter(){
            todoList = new ArrayList<>();
        }

        public void setList(ArrayList<ToDo> List){
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
    public void onBindViewHolder(SimpleItemViewHolder holder, final int position) {
        holder.setPosition(position);
        final ToDo todo = todoList.get(position);
        (holder).title.setText(todo.getName());

        holder.v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


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
                                onItemInteractionListener.onDeleteItem(uid , todo.getUid());


                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
               // todoLoadListener.onDataLoaded(getList());
                return true;
            }
        });

    }



        public final class SimpleItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView title;
            private int position;
            public View v;

            public void setPosition(int position){
                this.position = position;
            }
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

