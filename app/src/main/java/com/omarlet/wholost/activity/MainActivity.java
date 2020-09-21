package com.omarlet.wholost.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.omarlet.wholost.R;
import com.omarlet.wholost.adapter.ScoreRecyclerView;
import com.omarlet.wholost.model.Score;
import com.omarlet.wholost.model.winner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Score> scores = new ArrayList<>();
    private RecyclerView scoreList;
    private TextView meScore, youScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.addScore);

        meScore = findViewById(R.id.meScore);
        youScore = findViewById(R.id.youScore);

        scoreList = findViewById(R.id.scoreList);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setReverseLayout(true);
        lm.setStackFromEnd(true);
        scoreList.setLayoutManager(lm);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddScore.class);
                startActivity(intent);
            }
        });

        getScore();
        setupScoreList();
    }

    private int added = 0;

    private void getScore(){
        SharedPreferences pref = getSharedPreferences("score",MODE_PRIVATE);
        int amount = pref.getInt("amount",0);

        Gson gson = new Gson();

        for (int i = added; i < amount; i++) {
            String json = pref.getString("score"+i,"");
            assert json != null;
            if(!json.isEmpty()){
                Score score = gson.fromJson(json, Score.class);
                scores.add(score);
            }
        }

        added = amount;
    }

    private void setupScoreList(){
        scoreList.setAdapter(new ScoreRecyclerView(this,scores));
    }

    private void updateTotalScore() {
        float me = 0;
        float you = 0;
        for (Score score : scores) {
            if(score.winner == winner.me){
                me++;
            }else if (score.winner == winner.her){
                you++;
            } else {
                you += 0.5;
                me += 0.5;
            }
        }

        youScore.setText(String.valueOf(you));
        meScore.setText(String.valueOf(me));
        if(me > you){
            meScore.setTextColor(Color.parseColor("#AAFFA1"));
            youScore.setTextColor(Color.parseColor("#FF8275"));
        } else if(me < you) {
            meScore.setTextColor(Color.parseColor("#FF8275"));
            youScore.setTextColor(Color.parseColor("#AAFFA1"));
        } else {
            youScore.setTextColor(Color.parseColor("#FFFFFF"));
            meScore.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getScore();
        setupScoreList();
        updateTotalScore();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getScore();
        setupScoreList();
        updateTotalScore();
    }
}