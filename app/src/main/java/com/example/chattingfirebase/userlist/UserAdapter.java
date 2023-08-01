package com.example.chattingfirebase.userlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chattingfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    List<User> userList = new ArrayList<>();
    private OnItemClickEventListener mItemClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView description;
        ImageView image;

        ViewHolder(View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.nicknameText);
            description = itemView.findViewById(R.id.decriptionText);
            image = itemView.findViewById(R.id.profileImageView);

        }



    }
    UserAdapter(ArrayList<User> list) {
        userList = list;
    }

    public void setUserList(List<User> list){
        userList = list;
    }

    public List<User> getUserList(){
        return userList;
    }

    public void setOtherUser(){

    }

    UserAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_user, parent, false);
        ViewHolder vh = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.name.setText(user.getUserName());
        holder.description.setText(user.getDescription());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public interface OnItemClickEventListener {
        void onItemClick(View a_view, int a_position);
    }

    public void setItemClickListener(OnItemClickEventListener a_listener) {
        mItemClickListener = a_listener;
    }
}
