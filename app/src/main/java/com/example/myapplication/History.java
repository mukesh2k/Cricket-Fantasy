package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class History extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReferenceRDV;
    FirebaseAuth auth;
    EditText eMail, password;
    Button inscription ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listteam1);
        setContentView(R.layout.newusersigin);
        eMail = (EditText) findViewById(R.id.email26);
        password = (EditText) findViewById(R.id.password26);
        final int[] y = {0};
        inscription = (Button) findViewById(R.id.Createe);
        auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(eMail.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    System.out.println(y[0]++);
                    database = FirebaseDatabase.getInstance();
                    Object databaseReferenceRDV = database.getReference("user");
                    Toast.makeText(getApplicationContext(), "User created successfully", Toast.LENGTH_SHORT).show();
                    final String id = ((DatabaseReference) databaseReferenceRDV).push().getKey();
                    ((DatabaseReference) databaseReferenceRDV).child(id).setValue(eMail.getText().toString());

                } else {
                    System.out.println(y[0]++);
                    Toast.makeText(getApplicationContext(), "User could not be created because user may already exist    ", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

    }
}
