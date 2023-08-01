package com.example.chattingfirebase.chatlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chattingfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {
    FirebaseUser curUser;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chatlist, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.chatListRecyclerView);

        ChatListAdapter adapter = new ChatListAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        curUser = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference chatRooms = firebaseDatabase.getReference().child("ChatRooms").child(curUser.getUid());
        chatRooms.addValueEventListener(new ValueEventListener() { // 변화가 있을 때 마다 호춣
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<ChatRoom> chatRoomList = new ArrayList<>();

                for (DataSnapshot child : snapshot.getChildren()) {
                    ChatRoom chatRoom = child.getValue(ChatRoom.class);
                    chatRoomList.add(chatRoom);

                }
                adapter.setChatList(chatRoomList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }
}