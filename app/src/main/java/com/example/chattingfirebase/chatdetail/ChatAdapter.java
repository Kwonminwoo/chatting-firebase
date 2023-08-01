package com.example.chattingfirebase.chatdetail;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chattingfirebase.R;
import com.example.chattingfirebase.chatlist.ChatRoom;
import com.example.chattingfirebase.userlist.User;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    List<ChatItem> chatList = new ArrayList<>();
    private User otherUser;
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView message;

        ViewHolder(View itemView){
            super(itemView);



            name = itemView.findViewById(R.id.usernameTextView);
            message = itemView.findViewById(R.id.messageTextView);
        }



    }
    ChatAdapter(ArrayList<ChatItem> list) {
        chatList = list;
    }
    ChatAdapter() {
    }

    public void setChatList(List<ChatItem> list) {
        chatList = list;
    }

    public void setOtherUser(User user) {
        otherUser = user;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_chatroom, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatItem chat = chatList.get(position);

        if(chat.getUserId().equals(otherUser.getUserId())){ // 상대방이 보낸 것
            holder.name.setText(otherUser.getUserName());
            holder.name.setVisibility(View.VISIBLE); // 이름 보이게
            holder.message.setGravity(Gravity.START); // 왼쪽으로 붙게
            holder.message.setText(chat.getMessage());
        }else{ // 내가 보낸 것
            holder.name.setVisibility(View.INVISIBLE); // 이름 안보이게
            holder.message.setGravity(Gravity.END); // 오른쪽으로 붙게
            holder.message.setText(chat.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }
}
