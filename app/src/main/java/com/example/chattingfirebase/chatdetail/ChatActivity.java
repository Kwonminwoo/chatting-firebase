package com.example.chattingfirebase.chatdetail;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.chattingfirebase.R;
import com.example.chattingfirebase.chatlist.ChatListAdapter;
import com.example.chattingfirebase.userlist.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private String chatRoomId;
    private String otherUserId;
    private String myUserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        RecyclerView recyclerView = findViewById(R.id.chatRecyclerView);
        ChatAdapter adapter = new ChatAdapter();

        chatRoomId = getIntent().getStringExtra("chatRoomId");
        otherUserId = getIntent().getStringExtra("otherUserId");
        myUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase.getInstance().getReference().child("Users").child(myUserId).get()// 내 정보 가져옴
                .addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        User myUser = dataSnapshot.getValue(User.class);
                        String myUserName = myUser.getUserName();
                    }
                });
        FirebaseDatabase.getInstance().getReference().child("Users").child(otherUserId).get()// 상대방 정보 가져옴
                .addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        User otherUser = dataSnapshot.getValue(User.class);
                        adapter.setOtherUser(otherUser);
                    }
                });

        List<ChatItem> chatList = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("Chats").child(chatRoomId)
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { // 새로운 메시지가 생기면 호출됨
                        ChatItem chat = snapshot.getValue(ChatItem.class);
                        if (chat == null) {
                            return;
                        }
                        chatList.add(chat);
                        adapter.setChatList(chatList);
                        recyclerView.setAdapter(adapter);
                    }
                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {} // 변경 필요 없음
                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {} // 삭제 필요 없음
                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {} // 이동 필요 없음
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {} // 취소 필요 없음
                });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);
    }
}