package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class RecyclerViewPlayers2 {

        private Context mContext ;
        private PatientAdapter patientAdapter;

        public void setConfig(RecyclerView recyclerView, Context context, List<team> tea, List<Integer> keys)
        {
            mContext = context;
            patientAdapter = new PatientAdapter(tea, keys);
            recyclerView.setLayoutManager( new LinearLayoutManager( context ) );
            recyclerView.setAdapter( patientAdapter );

        }

        class PatientItemView extends RecyclerView.ViewHolder {

            private TextView name;
            private TextView role;
            private TextView pts;



            private Integer key;

            public PatientItemView(ViewGroup parent) {
                super( LayoutInflater.from( mContext ).
                        inflate( R.layout.cardteam, parent, false ) );


                name = (TextView) itemView.findViewById( R.id.playername);
                role = (TextView) itemView.findViewById( R.id.role);
                pts = (TextView) itemView.findViewById( R.id.Point);



                itemView.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(role.getText().toString()=="none")
                        {
                            switch (TeamSelection.radioGroup.getCheckedRadioButtonId())
                            {
                                case R.id.teambutton:
                                {
                                    if(TeamSelection.cur+Double.parseDouble(pts.getText().toString())>100) {
                                        Toast.makeText(TeamSelection.con, "This exceeds limit", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    role.setText("Player");
                                    TeamSelection.cur+=Double.parseDouble(pts.getText().toString());
                                    break;

                                }
                                case R.id.vicecaptain:
                                    if(TeamSelection.vc)
                                    {
                                        Toast.makeText(TeamSelection.con,"You have already selected vicecaptain", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    else
                                    {
                                        if(TeamSelection.cur+Double.parseDouble(pts.getText().toString())>100) {
                                            Toast.makeText(TeamSelection.con, "This exceeds limit", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        TeamSelection.vc=true;

                                        TeamSelection.cur+=Double.parseDouble(pts.getText().toString());
                                        role.setText("Vice Captain");
                                        break;

                                    }
                                case R.id.captainbutton:
                                    if(TeamSelection.c)
                                    {
                                        Toast.makeText(TeamSelection.con,"You have already selected captain", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    else
                                    {
                                        if(TeamSelection.cur+Double.parseDouble(pts.getText().toString())>100) {
                                            Toast.makeText(TeamSelection.con, "This exceeds limit", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        TeamSelection.cur+=Double.parseDouble(pts.getText().toString());
                                        TeamSelection.c=true;
                                        role.setText("Captain");
                                        break;

                                    }
                                default:
                                {

                                }
                            }
                            role.setTextColor(Color.GREEN);
                            pts.setTextColor(Color.GREEN );
                            name.setTextColor(Color.GREEN);

                        }
                        else
                        {
                            if(role.getText().toString()=="Player")
                            {
                                role.setText("none");
                                TeamSelection.cur-=Double.parseDouble(pts.getText().toString());

                            }else if(role.getText().toString()=="Vice Captain")
                            {
                                TeamSelection.vc=false;
                                role.setText("none");
                                TeamSelection.cur-=Double.parseDouble(pts.getText().toString());

                            }else if(role.getText().toString()=="Captain")
                            {
                                TeamSelection.c=false;
                                role.setText("none");
                                TeamSelection.cur-=Double.parseDouble(pts.getText().toString());

                            }
                            role.setTextColor(Color.BLACK);
                            pts.setTextColor(Color.BLACK);
                            name.setTextColor(Color.BLACK);
                        }

                        TeamSelection.ty.setText("Points:"+String.valueOf(TeamSelection.cur));

                    }
                } );

            }

            public void bind(team m, Integer key) {

               name.setText(m.getName());
               pts.setText(m.point.toString());
               role.setText(m.getTit());
                this.key = key;

            }
        }

        class PatientAdapter extends RecyclerView.Adapter<PatientItemView>{

            private List<team> patientList;
            private  List<Integer> keys;

            public PatientAdapter(List<team> patientList, List<Integer> keys) {
                this.patientList = patientList;
                this.keys = keys;
            }


            @NonNull
            @Override
            public PatientItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new PatientItemView( viewGroup );
            }

            @Override
            public void onBindViewHolder(@NonNull PatientItemView patientItemView, int i) {

                patientItemView.bind( patientList.get( i ), keys.get( i ) );
            }

            @Override
            public int getItemCount() {
                return patientList.size();
            }
        }

    }

