package controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sayed.todoapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import listener.TodoRegisterListener;
import module.ToDoUserRegister;

;

public class RegisterActivity extends AppCompatActivity implements TodoRegisterListener {
    TodoRegisterListener todoRegisterListener;
    ToDoUserRegister toDoUserRegister;
    private FirebaseAuth mAuth;
    @BindView(R.id.emailToRegister) EditText email;
    @BindView(R.id.firstPassword) EditText firstPassword;
    @BindView(R.id.secondPassword) EditText secondPassword;
    String email1 , Password1 , Password2;

    @Override
    protected void onStart() {
        super.onStart();
        todoRegisterListener = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
            ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        toDoUserRegister = ToDoUserRegister.getInstance();
    }
    public void completeRegister(View view){
        email1 = email.getText().toString() ;
        Password1 = firstPassword.getText().toString();
      //  Password2 = secondPassword.getText().toString();
        toDoUserRegister.RegisteringUserIntoDB(email1 , Password1 ,todoRegisterListener, this);
    }



    @Override
    public void onSucess() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.sendEmailVerification();

        Toast.makeText(RegisterActivity.this, "User Registered..Verifty your email to be able to login",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this , MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFailure() {

        Toast.makeText(RegisterActivity.this, "Authentication failed.",
                Toast.LENGTH_SHORT).show();

    }
}
