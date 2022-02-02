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

    private static final String KEY_EMAIL = "e_mail";





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
        // Inflate the layout for this fragment

        View  v = inflater.inflate(R.layout.fragment_third, container, false);

        FirstName = v.findViewById(R.id.FirstName);
        firstNameView = v.findViewById(R.id.firstNameView);
        LastName = v.findViewById(R.id.LastName);
        lastNameView = v.findViewById(R.id.lastNameView);
        mobile = v.findViewById(R.id.mobile);
        mobileView = v.findViewById(R.id.mobileView);
        emailView = v.findViewById(R.id.emailView);
        Gender = v.findViewById(R.id.Gender);
        genderView = v.findViewById(R.id.genderView);
        logout = v.findViewById(R.id.logOutButton);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("SHARED_PREFER", MODE_PRIVATE);

        String emailid = sharedPreferences.getString(KEY_EMAIL, null);

        if(emailid != null){
            emailView.setText(emailid);
        }


        DBHandler myDB = new DBHandler(getContext());
        SQLiteDatabase database = myDB.getReadableDatabase();

        Cursor userDetails = database.rawQuery("select * from user where emailid LIKE '" + emailid + "'", null);
        userDetails.moveToFirst();


        do{
            firstNameView.setText(userDetails.getString(0));
            lastNameView.setText(userDetails.getString(1));
            genderView.setText(userDetails.getString(5));
            emailView.setText(userDetails.getString(2));
            mobileView.setText(userDetails.getString(3));
        }
        while(userDetails.moveToNext());

        myDB.close();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
            }
        });




        return v ;
    }
}