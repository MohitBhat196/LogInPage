package com.example.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SecondFragment extends Fragment {

    RecyclerView femaleUsersView;
    UserListAdapter adapter;



    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        femaleUsersView = view.findViewById(R.id.femaleUsersView);

        DBHandler myDB = new DBHandler(getContext());
        adapter = new UserListAdapter(myDB.getUserData("Female"), getContext());

        femaleUsersView.setLayoutManager(new LinearLayoutManager(getContext()));

        femaleUsersView.setAdapter(adapter);

        return view;

    }
}