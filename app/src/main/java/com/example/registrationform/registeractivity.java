package com.example.registrationform;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registeractivity extends AppCompatActivity {
    EditText username,password;
    Button loginbutton;
    DBHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity);

        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        loginbutton= (Button) findViewById(R.id.loginbutton);
        final MediaPlayer mp=MediaPlayer.create(this,R.raw.hello);
        myDB=new DBHelper(this);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                if(user.equals("")||pass.equals("")){
                    Toast.makeText(registeractivity.this, "Please enter the credentials", Toast.LENGTH_SHORT).show();
                }
                else{
                   Boolean result= myDB.checkusernamepassword(user,pass);
                   if(result==true){
                       Intent i=new Intent(getApplicationContext(),HomeActivity.class);
                       startActivity(i);
                       mp.start();

                   }

                   else{
                       Toast.makeText(registeractivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                   }
                }

            }
        });

    }
}



