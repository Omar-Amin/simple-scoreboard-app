package com.omarlet.wholost.model;

import android.os.Build;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;

public class Score {
    private String name, date;
    private float scoreMe, scoreYou;
    public winner winner;

    public Score(String name, float scoreYou, float scoreMe, String date){
        this.name = name;
        this.scoreYou = scoreYou;
        this.scoreMe = scoreMe;
        if (scoreYou < scoreMe){
            winner = com.omarlet.wholost.model.winner.me;
        } else if (scoreYou > scoreMe){
            winner = com.omarlet.wholost.model.winner.her;
        } else {
            winner = com.omarlet.wholost.model.winner.draw;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime localTime = LocalDateTime.now();
            this.date = localTime.getHour() + ":" + localTime.getMinute() + "  ";
            this.date += date;
        }
    }

    public float getScoreMe() {
        return scoreMe;
    }

    public float getScoreYou() {
        return scoreYou;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String toString() {
        return "Name: " + name + "\nMe: " + scoreMe + "\nHer: " + scoreYou + "\nDate: " + date;
    }
}
