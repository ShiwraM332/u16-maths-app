package com.u16.MobileMathsApp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreScene extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_activity);

        TextView score = findViewById(R.id.score);
        TextView encouragement = findViewById(R.id.encouragement);

        int scoreCount = getScoreCount();

        // display score
        score.setText(score.getText() + String.valueOf(scoreCount));

        String good = "Well done";
        String middle = "You could do better";
        String bad = "Practice more";

        // Check score and give encouragement based on it
        if (scoreCount >= 7) {
            encouragement.setText(good);
        } else if (scoreCount >= 4) {
            encouragement.setText(middle);
        } else {
            encouragement.setText(bad);
        }

    }
    private int getScoreCount() { // method to get the users score count
        SharedPreferences sharedPreferences = getSharedPreferences("storage", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("score", 0);
    }
}
