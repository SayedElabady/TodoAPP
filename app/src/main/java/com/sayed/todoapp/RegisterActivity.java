package com.sayed.todoapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    @BindView(R.id.emailToRegister) EditText email;
    @BindView(R.id.firstPassword) EditText firstPassword;
    @BindView(R.id.secondPassword) EditText secondPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
            ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
    }
    public void completeRegister(View view){
        String Email = email.getText().toString() , Password1 = firstPassword.getText().toString(),
        Password2 = secondPassword.getText().toString();
        if(Password1.equals(Password2)) {
            mAuth.createUserWithEmailAndPassword(Email, Password1)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(RegisterActivity.this, "User Registered sa7 .. Mabrouk yasta!",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this , MainActivity.class);
                                startActivity(intent);
                            } else {

                                Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }
        else {
            Toast.makeText(RegisterActivity.this, "Passwords don't match! please re Enter them.",
                    Toast.LENGTH_SHORT).show();
        }



    }
}
