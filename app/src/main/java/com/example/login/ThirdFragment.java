package com.example.login;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ThirdFragment extends Fragment {

    TextView emailView;
    TextView mobileView;
    TextView genderView;
    TextView firstNameView;
    TextView lastNameView;
    TextView FirstName;
    TextView LastName;
    TextView Gender;
    TextView mobile;
    Button logout;

    private static final String KEY_EMAIL = "email";

    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View  view = inflater.inflate(R.layout.fragment_third, container, false);
        firstNameView = view.findViewById(R.id.firstNameView);
        lastNameView = view.findViewById(R.id.lastNameView);
        emailView = view.findViewById(R.id.emailView);
        mobileView = view.findViewById(R.id.mobileView);
        genderView = view.findViewById(R.id.genderView);
        FirstName = view.findViewById(R.id.FirstName);
        LastName = view.findViewById(R.id.LastName);
        mobile = view.findViewById(R.id.mobile);
        Gender = view.findViewById(R.id.Gender);
        logout = view.findViewById(R.id.logOutButton);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("SHARED_PREFER", MODE_PRIVATE);

        String emailId = sharedPreferences.getString(KEY_EMAIL, null);

        if(emailId != null){
            emailView.setText(emailId);
        }


        DBHandler db = new DBHandler(getContext());
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor userDetails = database.rawQuery("select * from user where emailid LIKE '" + emailId + "'", null);
        userDetails.moveToFirst();


        do{
            firstNameView.setText(userDetails.getString(1));
            lastNameView.setText(userDetails.getString(2));
            emailView.setText(userDetails.getString(3));
            genderView.setText(userDetails.getString(6));
            mobileView.setText(userDetails.getString(5));
        }
        while(userDetails.moveToNext());

        db.close();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
            }
        });




        return view ;


    }
}