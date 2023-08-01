package com.example.chattingfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.chattingfirebase.chatlist.ChatFragment;
import com.example.chattingfirebase.userlist.UserFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FrameLayout cont;
    UserFragment userFragment;
    ChatFragment chatFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser curUser = FirebaseAuth.getInstance().getCurrentUser();
//        Log.e("email", curUser.getEmail());
        if(curUser == null){
            // 로그인 x
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

//        auth.signOut();

        userFragment = new UserFragment();
        chatFragment = new ChatFragment();
        cont = findViewById(R.id.frame);
        replaceFragment();
    }

    private void replaceFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, userFragment).commit();

        NavigationBarView navigationBarView = findViewById(R.id.bottomNavi);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.userList){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, userFragment).commit();
                }else if(item.getItemId() == R.id.chatRoomList){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, chatFragment).commit();
                }
                return false;
            }
        });
    }
}