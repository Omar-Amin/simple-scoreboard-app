package com.omarlet.wholost.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omarlet.wholost.R;
import com.omarlet.wholost.model.Score;
import com.omarlet.wholost.model.winner;

import java.util.ArrayList;

public class ScoreRecyclerView extends RecyclerView.Adapter<ScoreRecyclerView.ScoreViewHolder>{

    private Context context;
    private ArrayList<Score> scores;

    public ScoreRecyclerView(Context context, ArrayList<Score> scores){
        this.context = context;
        this.scores = scores;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.score_item,parent,false);
        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        Score score = scores.get(position);
        holder.scoreName.setText(score.getName());
        holder.date.setText(score.getDate());
        holder.pointYou.setText(String.valueOf(score.getScoreYou()));
        holder.pointMe.setText(String.valueOf(score.getScoreMe()));

        if(score.winner == winner.me){
            holder.pointYou.setTextColor(Color.parseColor("#8CFF4F59"));
            holder.pointMe.setTextColor(Color.parseColor("#8C90BC4B"));
        } else if (score.winner == winner.draw){
            holder.pointYou.setTextColor(Color.parseColor("#FF979797"));
            holder.pointMe.setTextColor(Color.parseColor("#FF979797"));
        }
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    static class ScoreViewHolder extends RecyclerView.ViewHolder{

        private TextView pointMe, pointYou, date, scoreName;

        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);

            pointMe = itemView.findViewById(R.id.pointMe);
            pointYou = itemView.findViewById(R.id.pointYou);
            date = itemView.findViewById(R.id.date);
            scoreName = itemView.findViewById(R.id.scoreName);
        }
    }
}
