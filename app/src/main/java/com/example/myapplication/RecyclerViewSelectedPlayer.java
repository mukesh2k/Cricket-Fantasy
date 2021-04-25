package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewSelectedPlayer {
    private Context mContext;
    private RecyclerViewSelectedPlayer.PatientAdapter pointsAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<SelectedPlayer> tea, List<Integer> keys) {
        mContext = context;
        pointsAdapter = new RecyclerViewSelectedPlayer.PatientAdapter(tea, keys);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        recyclerView.setAdapter(pointsAdapter);
    }

    class PatientItemView extends RecyclerView.ViewHolder {

        private TextView point;
        private TextView team;
        private TextView indpt;
        private TextView player;


        private Integer key;

        public PatientItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.cardplayer, parent, false));


            point = (TextView) itemView.findViewById(R.id.pttz);
            team = (TextView) itemView.findViewById(R.id.tme);
            indpt = (TextView) itemView.findViewById(R.id.indpt);
            player = (TextView) itemView.findViewById(R.id.nme);


        }

        public void bind(SelectedPlayer m, Integer key) {

            point.setText(m.getScored()+'\n'+m.getRole());//TODO
            indpt.setText(m.getValue());
            player.setText(m.getName());
            team.setText(m.getTeam());
            this.key = key;

        }

    }
    class PatientAdapter extends RecyclerView.Adapter<RecyclerViewSelectedPlayer.PatientItemView> {

        private List<SelectedPlayer> points;
        private List<Integer> keys;

        public PatientAdapter(List<SelectedPlayer> patientList, List<Integer> keys) {
            this.points = patientList;
            this.keys = keys;
        }


        @NonNull
        @Override
        public RecyclerViewSelectedPlayer.PatientItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new RecyclerViewSelectedPlayer.PatientItemView(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewSelectedPlayer.PatientItemView patientItemView, int i) {

            patientItemView.bind(points.get(i), keys.get(i));
        }

        @Override
        public int getItemCount() {
            return points.size();
        }
    }
}