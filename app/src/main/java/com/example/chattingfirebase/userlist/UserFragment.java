package com.example.chattingfirebase.userlist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.chattingfirebase.R;
import com.example.chattingfirebase.chatlist.ChatRoom;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Fragment {
    FirebaseUser curUser;
    List<User> userList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user, container, false);
        curUser = FirebaseAuth.getInstance().getCurrentUser();


        RecyclerView recyclerView = root.findViewById(R.id.userRecyclerView);

        UserAdapter adapter = new UserAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() { // 한번만 호출
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<User> userList = new ArrayList<>();
                for (DataSnapshot child : snapshot.getChildren()) {
                    Log.e("child", child.getKey());
                    User user = child.getValue(User.class);
                    Log.e("user", user.toString());
//                    if(user.getUID().equals(curUser.getUid())){
//                        continue; // 자기 자신은 건너 뜀
//                    }
                    userList.add(user);
                    Log.e("user", user.getUserName());
                    adapter.setUserList(userList); // 어댑터 리스트 초기화
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

    }
});

        adapter.setItemClickListener(new UserAdapter.OnItemClickEventListener() {
            @Override
            public void onItemClick(View a_view, int a_position) {
                String myUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                User selectUser = adapter.getUserList().get(a_position);
                DatabaseReference chatRoomsDB = FirebaseDatabase.getInstance().getReference().child("ChatRooms").child(myUserId).child(selectUser.getUserId());
                chatRoomsDB.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        String chatRoomId;
                        if(dataSnapshot.getValue(ChatRoom.class) != null){
                            ChatRoom chatRoom = dataSnapshot.getValue(ChatRoom.class);
                            chatRoomId = chatRoom.getChatRoomId();

                        }else{

                        }
                    }
                })
            }
        });

        return root;
    }
}