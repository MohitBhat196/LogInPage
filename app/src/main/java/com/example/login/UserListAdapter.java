package com.example.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    ArrayList<ModelClass> List;
        Context context;

    public UserListAdapter(ArrayList<ModelClass> arrayList, Context context) {
        List = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.user_list_items,parent,false);
        ViewHolder viewholder = new ViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.ViewHolder holder, int position) {

        ModelClass modelClass = List.get(position);
        holder.cardFirstName.setText(modelClass.getFirstName());
        holder.cardLastName.setText(modelClass.getLastName());
        holder.cardEmail.setText(modelClass.getEmail());

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cardFirstName;
        TextView cardLastName;
        TextView cardEmail;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardFirstName = itemView.findViewById(R.id.cardFirstName);
            cardLastName = itemView.findViewById(R.id.cardLastName);
            cardEmail = itemView.findViewById(R.id.cardEmail);


        }
    }
}
