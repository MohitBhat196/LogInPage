package com.example.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SecondFragment extends Fragment{

    RecyclerView femaleView;
    UserListAdapter adapter;

    public SecondFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_second, container, false);
        femaleView = view.findViewById(R.id.femaleView);

        DBHandler db = new DBHandler(getContext());
        adapter = new UserListAdapter(db.getUserData("Female"),getContext());

        femaleView.setLayoutManager(new LinearLayoutManager(getContext()));

        femaleView.setAdapter(adapter);


        return view;
    }
}