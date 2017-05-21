package module;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import controller.RegisterActivity;
import listener.TodoRegisterListener;

/**
 * Created by Sayed on 5/21/2017.
 */
public class ToDoUserRegister {
    private static ToDoUserRegister ourInstance;

    public static ToDoUserRegister getInstance() {
        if(ourInstance == null)
            ourInstance =   new ToDoUserRegister();

        return ourInstance;
    }

    private ToDoUserRegister() {
    }
   public void RegisteringUserIntoDB(String email , String password , final TodoRegisterListener listener , RegisterActivity reg){

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(reg, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                listener.onSucess();

                            } else {
                                listener.onFailure();
                            //    Toast.makeText(RegisterActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }




    }

