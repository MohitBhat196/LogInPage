package com.example.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    EditText firstName;
    EditText lastName;
    EditText emailId;
    EditText password;
    EditText phone;
    DBHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("SIGNUP");

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        emailId = findViewById(R.id.email1);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        Button register = findViewById(R.id.register);
        Button view = findViewById(R.id.viewButton);

        Button cancelButton = findViewById(R.id.cancelButton);

        Button backToLogin = findViewById(R.id.backToLogin);
        db = new DBHandler(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstNameValue = firstName.getText().toString();
                String lastNameValue = lastName.getText().toString();
                String email1Value = emailId.getText().toString();
                String password1Value = password.getText().toString();
                String phoneValue = phone.getText().toString();

                Pattern names = Pattern.compile("^[a-zA-Z]+$");
                Pattern mobileNumber = Pattern.compile("^[0-9]{10}+$");
                Pattern mailId = Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9]+.+[a-zA-Z]+$");
                Pattern passcode = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$");

                Matcher firstNameValueMatch = names.matcher(firstNameValue);
                boolean firstNameValueMatches = firstNameValueMatch.matches();


                Matcher lastNameValueMatch = names.matcher(lastNameValue);
                boolean lastNameValueMatches = lastNameValueMatch.matches();

                Matcher email1ValueMatch = mailId.matcher(email1Value);
                boolean email1ValueMatches = email1ValueMatch.matches();

                Matcher phoneValueMatch = mobileNumber.matcher(phoneValue);
                boolean phoneValueMatches = phoneValueMatch.matches();

                Matcher password1ValueMatch = passcode.matcher(password1Value);
                boolean password1ValueMatches = password1ValueMatch.matches();

                if (firstNameValue.equals("") || lastNameValue.equals("") || email1Value.equals("") || password1Value.equals("") || phoneValue.equals("")) {
                    Toast.makeText(SignUp.this, "FILL REQUIRED DETAILS", Toast.LENGTH_SHORT).show();
                }

                if (!firstNameValueMatches || !lastNameValueMatches || !email1ValueMatches || !phoneValueMatches || !password1ValueMatches) {
                    if (!firstNameValueMatches) {
                        Toast.makeText(SignUp.this, "Please enter your First Name correctly", Toast.LENGTH_LONG).show();
                    } else if (!lastNameValueMatches) {
                        Toast.makeText(SignUp.this, "Please enter your Last Name correctly", Toast.LENGTH_LONG).show();
                    } else if (!email1ValueMatches) {
                        Toast.makeText(SignUp.this, "Please enter your Email Id correctly", Toast.LENGTH_LONG).show();

                    } else if (!phoneValueMatches) {
                        Toast.makeText(SignUp.this, "Please enter your Phone Number correctly(Use only digits!!)", Toast.LENGTH_LONG).show();
                    } else if (!password1ValueMatches) {
                        Toast.makeText(SignUp.this, "Use at least one lowercase, one uppercase, one special character and one number", Toast.LENGTH_LONG).show();
                    }
                } else {

                    //if (firstNameValue.equals("") || lastNameValue.equals("") || email1Value.equals("") || password1Value.equals("") || phoneValue.equals("")) {
                    //Toast.makeText(SignUp.this, "FILL REQUIRED DETAILS", Toast.LENGTH_SHORT).show();
                    Boolean checkEmailId = db.checkEmail(email1Value);
                    if (checkEmailId == false) {
                        boolean isInserted = db.Insert(email1Value, password1Value, firstNameValue, lastNameValue, phoneValue);
                        if (isInserted == true) {
                            Toast.makeText(SignUp.this, "REGISTERED", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignUp.this, "NOT REGISTERED", Toast.LENGTH_SHORT).show();


                        }
                    } else {
                        Toast.makeText(SignUp.this, "ALREADY REGISTERED", Toast.LENGTH_SHORT).show();
                    }

                }
            }
          });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = db.getData();
                if(cursor.getCount() == 0){
                    showMessage("Error", "Nothing Found");
                    return;}

                    StringBuffer buffer = new StringBuffer();
                    while(cursor.moveToNext()){
                        buffer.append("ID : "+ cursor.getString(0)+"\n");
                        buffer.append("Email : "+ cursor.getString(1)+"\n");
                        buffer.append("Password : "+ cursor.getString(2)+"\n");
                        buffer.append("First Name : "+ cursor.getString(3)+"\n");
                        buffer.append("Last Name : "+ cursor.getString(4)+"\n");
                        buffer.append("Phone number : "+ cursor.getString(5)+"\n");
                    }
                    showMessage("REGISTERED DATA", buffer.toString());
                }



        });



        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyField();
            }


        });

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUp.this, LoginActivity.class);
                startActivity(i);
            }
        });




        }
    public void emptyField() {
        firstName.setText("");
        lastName.setText("");
        emailId.setText("");
        password.setText("");
        phone.setText("");
    }

    public void showMessage(String title, String Message) {
      AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();


    }


}

