package module;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import controller.RegisterActivity;
import listener.TodoRegisterListener;

public class ToDoUserRegister {
    private static ToDoUserRegister Instance;

    public static ToDoUserRegister getInstance() {
        if(Instance == null)
            Instance =   new ToDoUserRegister();

        return Instance;
    }

    private ToDoUserRegister() {
    }
   public void RegisterUser(String email , String password , final TodoRegisterListener todoRegisterListener){

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                todoRegisterListener.onSuccess();

                            } else {
                                todoRegisterListener.onFailure();

                            }

                        }
                    });
        }




    }

