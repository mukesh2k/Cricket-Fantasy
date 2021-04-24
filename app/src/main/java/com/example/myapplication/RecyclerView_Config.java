package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class RecyclerView_Config {

        private Context mContext ;
        private PatientAdapter patientAdapter;

        public void setConfig(RecyclerView recyclerView, Context context, List<MatchArray> patients, List<String> keys)
        {
            mContext = context;
            patientAdapter = new PatientAdapter(patients, keys);
            recyclerView.setLayoutManager( new LinearLayoutManager( context ) );
            recyclerView.setAdapter( patientAdapter );

        }

        class PatientItemView extends RecyclerView.ViewHolder {

            private TextView venu;
            private TextView vs;
            private TextView date;
            private TextView type;
            private TextView time;


            private String key;

            public PatientItemView(ViewGroup parent) {
                super( LayoutInflater.from( mContext ).
                        inflate( R.layout.cardmatch, parent, false ) );


                venu = (TextView) itemView.findViewById( R.id.venue);
                vs = (TextView) itemView.findViewById( R.id.vs);
                date = (TextView) itemView.findViewById( R.id.date);
                type = (TextView) itemView.findViewById( R.id.type );
                time = (TextView) itemView.findViewById( R.id.time );


                itemView.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent( mContext, TeamSelection.class );
                        intent.setFlags( FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity( intent );

                    }
                } );

            }

            public void bind(MatchArray m, String key) {

                venu.setText( m.getVenue() );
                vs.setText( m.getTeam1()+" VS "+m.getTeam2() );
                date.setText(m.getDate());
                type.setText( m.getType());
                time.setText( m.getTime() );
                this.key = key;

            }
        }

        class PatientAdapter extends RecyclerView.Adapter<PatientItemView>{

            private List<MatchArray> patientList;
            private  List<String> keys;

            public PatientAdapter(List<MatchArray> patientList, List<String> keys) {
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

