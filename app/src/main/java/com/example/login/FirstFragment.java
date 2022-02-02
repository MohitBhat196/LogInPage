package com.example.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FirstFragment extends Fragment{

    RecyclerView maleView;
    UserListAdapter adapter;

            public FirstFragment(){

            }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_first, container, false);
        maleView = view.findViewById(R.id.maleView);

        DBHandler db = new DBHandler(getContext());
        adapter = new UserListAdapter(db.getUserData("Male"),getContext());

        maleView.setLayoutManager(new LinearLayoutManager(getContext()));

        maleView.setAdapter(adapter);


        return view;
    }
}