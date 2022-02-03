package com.example.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FirstFragment extends Fragment {

    RecyclerView maleUsersView;
    UserListAdapter adapter;

    public FirstFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        maleUsersView = view.findViewById(R.id.maleUsersView);

        DBHandler myDB = new DBHandler(getContext());
        adapter = new UserListAdapter(myDB.getUserData("Male"), getContext());

        maleUsersView.setLayoutManager(new LinearLayoutManager(getContext()));

        maleUsersView.setAdapter(adapter);

        return view;
    }
}