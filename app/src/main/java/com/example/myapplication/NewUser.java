package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class NewUser extends AppCompatActivity {

    FirebaseAuth auth;
    EditText eMail, password;
    Button inscription ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.newusersigin);

         eMail = (EditText) findViewById( R.id.email26) ;
         password = (EditText) findViewById( R.id.password26 ) ;
         inscription = (Button) findViewById( R.id.Create)  ;

        auth = FirebaseAuth.getInstance();


    }

    public void  createUser(View v)
    {
        if (eMail.getText().toString().equals( "" ) || password.getText().toString().equals( "" )) {
            Toast.makeText( getApplicationContext(), "Blank not allowed", Toast.LENGTH_SHORT ).show();
        } else {
            FirebaseAuth auth = FirebaseAuth.getInstance();

            auth.createUserWithEmailAndPassword(eMail.getText().toString(),  password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "User created successfully", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(getApplicationContext(), "User could not be created because user may already exist    ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            });



        }
    }

    }

