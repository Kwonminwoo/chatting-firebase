package com.example.chattingfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser curUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText emailEdit = findViewById(R.id.emailEditText);
        EditText passEdit = findViewById(R.id.passwordEditText);
        Button signUpBtn = findViewById(R.id.signUpBtn);
        Button signInBtn = findViewById(R.id.signInBtn);

        Log.e("test", "here");
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        // 회원 가입
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp(emailEdit.getText().toString(), passEdit.getText().toString());
            }
        });

        // 로그인
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(emailEdit.getText().toString(), passEdit.getText().toString());
            }
        });
    }

    private void signUp(String email, String password) {
        auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser curUser = auth.getCurrentUser();
                        }else{
                            Log.e("sign up", task.getException().toString());
                            Toast.makeText(LoginActivity.this, "error sign up", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signIn(String email, String password){
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            curUser = auth.getCurrentUser();

                            Map<String, Object> user = new HashMap<>();
                            user.put("userId", curUser.getUid());
                            user.put("userName", email);
                            firebaseDatabase.getReference().child("Users").child(curUser.getUid()).updateChildren(user);

                            Toast.makeText(LoginActivity.this, "로그인 완료", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Log.e("test", "Here");
                            Log.e("login err", task.getException().getMessage());
                            Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}