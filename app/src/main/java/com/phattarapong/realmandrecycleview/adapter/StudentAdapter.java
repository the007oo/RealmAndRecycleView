package com.phattarapong.realmandrecycleview.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phattarapong.realmandrecycleview.R;
import com.phattarapong.realmandrecycleview.model.Student;

import io.realm.RealmResults;

/**
 * Created by Phattarapong on 25-Jul-17.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    RealmResults<Student> realmResults;

    public StudentAdapter(RealmResults realmResults) {
        this.realmResults = realmResults;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_student, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Student student = realmResults.get(position);
        holder.tvName.setText("Name : " + student.getName());
        holder.tvId.setText("Id : " + student.getIdNumber());
        holder.tvScore.setText("Score : " + student.getScore());
    }

    @Override
    public int getItemCount() {
        return realmResults.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvId, tvScore;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvId = (TextView) itemView.findViewById(R.id.tv_id);
            tvScore = (TextView) itemView.findViewById(R.id.tv_score);

        }
    }

}
