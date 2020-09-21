package com.omarlet.wholost.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.omarlet.wholost.R;
import com.omarlet.wholost.model.Score;
import com.omarlet.wholost.ui.DateDialog;

import java.time.LocalDateTime;

public class AddScore extends AppCompatActivity {

    private EditText me, her, gameName;
    private DateDialog dateDialog;
    private String date = "";
    private TextView calenderholder;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_score);

        me = findViewById(R.id.scoreOne);
        her = findViewById(R.id.scoreTwo);
        gameName = findViewById(R.id.gameName);

        LocalDateTime localTime = LocalDateTime.now();
        date = localTime.getDayOfMonth() + "-" + localTime.getMonthValue() + "-" + localTime.getYear();

        calenderholder = findViewById(R.id.calenderHolder);
        calenderholder.setText(date);

        RelativeLayout chooseDate = findViewById(R.id.chooseDate);

        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateDialog = new DateDialog(AddScore.this);
                dateDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        date = dateDialog.getDate();
                        calenderholder.setText(date);
                    }
                });

                dateDialog.show();
            }
        });

        Button addScore = findViewById(R.id.addNewScore);

        addScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!me.getText().toString().isEmpty() && !her.getText().toString().isEmpty() && !gameName.getText().toString().isEmpty()){
                    Score score = calculateWinner();
                    saveScore(score);
                    finish();
                } else {
                    Toast.makeText(AddScore.this,"Fill out the form", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private Score calculateWinner() {
        String game = gameName.getText().toString();
        float meS = Float.parseFloat(me.getText().toString());
        float herS = Float.parseFloat(her.getText().toString());
        return new Score(game,herS,meS,date);
    }

    private void saveScore(Score score){
        SharedPreferences pref = getSharedPreferences("score",MODE_PRIVATE);
        int amount = pref.getInt("amount",0);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(score);
        editor.putString("score"+amount, json);
        editor.putInt("amount",amount+1);
        editor.apply();
    }


}