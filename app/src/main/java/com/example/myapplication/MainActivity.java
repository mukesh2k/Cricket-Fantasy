package com.example.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static String k;
    FirebaseAuth auth;
    Button b1, b3,b2;
    EditText txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ProgressBar p;
        b1 = (Button) findViewById(R.id.emai);
        b2=(Button)findViewById(R.id.face);
        b3 = (Button) findViewById(R.id.phone);

        auth = FirebaseAuth.getInstance();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(com.example.myapplication.MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.login_patient, null);

                final EditText mEmail = (EditText) mView.findViewById(R.id.email25);
                final EditText mPassword = (EditText) mView.findViewById(R.id.password);

                Button mLogin = (Button) mView.findViewById(R.id.login);
                mLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (mEmail.getText().toString().equals("") || mPassword.getText().toString().equals("")) {
                            Snackbar.make(v, "Blank fields not allowed", 3000).show();

                        } else {

                                auth.signInWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {

                                        } else {
                                            Toast.makeText(getApplicationContext(), "User could not be logged in", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                        }

                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();

                dialog.show();

            }


        });

        b3.setOnClickListener(new View.OnClickListener() {
            //phone
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(com.example.myapplication.MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.phonesignin, null);
                Button mInsc=findViewById(R.id.insp);
                final EditText mEmail = (EditText) mView.findViewById(R.id.phonne);
                Button mLogin = (Button) mView.findViewById(R.id.phonotp);

                //connecter et verification des champs

                mLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (mEmail.getText().toString().equals("") ) {
                            Toast.makeText(getApplicationContext(), "Blank fields not allowed", Toast.LENGTH_SHORT).show();
                        } else {

                        }

                    }
                });

                //inscription des patient

                mInsc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(getApplicationContext(), NewUser.class);
                        startActivity(i);

                    }
                });


                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

            }


        });


    }



}

