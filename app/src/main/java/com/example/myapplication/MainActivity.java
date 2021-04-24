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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MainActivity extends AppCompatActivity {


    public static String k;
    FirebaseAuth auth;
    Button b1, b3,b2;
    EditText txt;
    public static ArrayList<Success>S=new ArrayList<>();
    public static ArrayList<String>ky=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.emai);
        b2=(Button)findViewById(R.id.face);
        b3 = (Button) findViewById(R.id.phone);

        auth = FirebaseAuth.getInstance();

        auth = FirebaseAuth.getInstance();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(com.example.myapplication.MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.login_patient, null);

                final EditText mEmail = (EditText) mView.findViewById(R.id.email25);
                final EditText mPassword = (EditText) mView.findViewById(R.id.password25);

                Button mLogin = (Button) mView.findViewById(R.id.login);

                Button mInsc=mView.findViewById(R.id.insp);
                mInsc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i=new Intent(getApplicationContext(),NewUser.class);
                        i.setFlags(FLAG_ACTIVITY_NEW_TASK );
                        getApplicationContext().startActivity(i);
//                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(com.example.myapplication.MainActivity.this);
//                        View mView = getLayoutInflater().inflate(R.layout.newusersigin, null);
//
//                        mBuilder.setView(mView);
//                        AlertDialog dialog = mBuilder.create();
//
//                        dialog.show();
                    }



                });
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
                                            FirebaseDatabase database;
                                            DatabaseReference databaseReference;
                                            database = FirebaseDatabase.getInstance();
                                            databaseReference = database.getReference( "users");


                                            databaseReference.addValueEventListener( new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                    S.clear();
                                                    Welcome.id=(mEmail.getText().toString());
                                                    for (DataSnapshot keyNode : dataSnapshot.getChildren())
                                                    {
                                                        ky.add(keyNode.getKey());
                                                        Success s= keyNode.getValue(Success.class);
                                                        if(s.getKey()==(mEmail.getText().toString()))
                                                        S.add( s );
                                                    }
                                                    Intent i=new Intent(getApplicationContext(),Welcome.class);
                                                    i.setFlags(FLAG_ACTIVITY_NEW_TASK );
                                                    i.putExtra("key", mEmail.getText().toString());
                                                    getApplicationContext().startActivity(i);

                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            } );
                                        }

                                         else {
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
//                mInsc.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(com.example.myapplication.MainActivity.this);
//                        View mView = getLayoutInflater().inflate(R.layout.newusersigin, null);
//
//                        mBuilder.setView(mView);
//                        AlertDialog dialog = mBuilder.create();
//
//                        dialog.show();
//                    }
//                });


            }



        });

        b3.setOnClickListener(new View.OnClickListener() {
            //phone
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(com.example.myapplication.MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.phonesignin, null);

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



            }


        });



    }



}

