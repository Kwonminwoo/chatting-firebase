package com.example.chattingfirebase.chatlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chattingfirebase.R;

import java.util.ArrayList;
import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {
    List<ChatRoom> chatList = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView lastMessage;
        ImageView image;

        ViewHolder(View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.otherNicknameText);
            lastMessage = itemView.findViewById(R.id.lastMessageText);
            image = itemView.findViewById(R.id.otherProfileImageView);
        }



    }
    ChatListAdapter(ArrayList<ChatRoom> list) {
        chatList = list;
    }
    ChatListAdapter() {
    }

    public void setChatList(List<ChatRoom> list) {
        chatList = list;
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
        ChatRoom user = chatList.get(position);
        holder.name.setText(user.getLastMessage());
        holder.lastMessage.setText(user.getOtherUserName());
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }
}
