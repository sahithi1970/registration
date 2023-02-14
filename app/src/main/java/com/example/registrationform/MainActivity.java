package com.example.registrationform;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText username1, password1, cpassword1,phoneno1;
    Button loginbutton1, registerbutton1;
    DBHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username1 = (EditText) findViewById(R.id.username);
        password1 = (EditText)findViewById(R.id.password);
        cpassword1= (EditText)findViewById(R.id.cpassword);
        phoneno1=(EditText)findViewById(R.id.phoneno);
        loginbutton1=(Button)findViewById(R.id.loginbutton);
        registerbutton1=(Button)findViewById(R.id.registerbutton);
        final MediaPlayer mp=MediaPlayer.create(this,R.raw.regi);

        myDB=new DBHelper(this);
        registerbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username1.getText().toString();
                String pass = password1.getText().toString();
                String cpass = cpassword1.getText().toString();
                String phn = phoneno1.getText().toString();
                if (user.equals("") || pass.equals("") || cpass.equals("") || phn.equals("")) {
                    Toast.makeText(MainActivity.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals(cpass)) {
                        Boolean usercheckresult = myDB.checkusername(user);
                        if (usercheckresult == false) {
                            Boolean regResult = myDB.insertData(user, pass, phn);
                            if (regResult == true) {

                                Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), registeractivity.class);
                                startActivity(i);
                                mp.start();


                            } else {
                                Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "User already exist\n Please Login", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(MainActivity.this, "Password not matched", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        loginbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),registeractivity.class);
                startActivity(i);
            }
        });
    }
}