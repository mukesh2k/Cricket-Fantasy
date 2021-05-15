package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewPoints {
    private Context mContext;
    private RecyclerViewPoints.PatientAdapter pointsAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<PointInfo> tea, List<Integer> keys) {
        mContext = context;
        pointsAdapter = new PatientAdapter(tea, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(pointsAdapter);

    }
    public void fun()
    {
        pointsAdapter.notifyDataSetChanged();
    }
    class PatientItemView extends RecyclerView.ViewHolder {

        private TextView over;
        private TextView info;
        private TextView pts;
        private TextView player;


        private Integer key;

        public PatientItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.cardpoint, parent, false));


            over = (TextView) itemView.findViewById(R.id.over);
            info = (TextView) itemView.findViewById(R.id.inf);
            pts = (TextView) itemView.findViewById(R.id.pt);
            player = (TextView) itemView.findViewById(R.id.play);


        }
        public void bind(PointInfo m, Integer key){

            over.setText(m.getOver());
            pts.setText(m.getGain());
            player.setText(m.getPlayer()+'\n'+m.getTeam());
            info.setText(m.getInfo());
            this.key = key;

        }

    }

    class PatientAdapter extends RecyclerView.Adapter<PatientItemView> {

        private List<PointInfo> points;
        private List<Integer> keys;

        public PatientAdapter(List<PointInfo> patientList, List<Integer> keys) {
            this.points = patientList;
            this.keys = keys;
        }


        @NonNull
        @Override
        public RecyclerViewPoints.PatientItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new RecyclerViewPoints.PatientItemView(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewPoints.PatientItemView patientItemView, int i) {

            patientItemView.bind(points.get(i), keys.get(i));
        }

        @Override
        public int getItemCount() {
            return points.size();
        }
    }
}