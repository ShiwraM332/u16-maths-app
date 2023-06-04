package com.u16.MobileMathsApp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DifficultyScene extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.difficulty_activity);

        Button easyBtn = findViewById(R.id.easy_btn);
        Button mediumBtn = findViewById(R.id.medium_btn);
        Button hardBtn = findViewById(R.id.hard_btn);

        easyBtn.setOnClickListener(v -> {
            // start next scene and store data
            SharedPreferences sharedPreferences = getSharedPreferences("storage", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("time", 30);
            editor.apply();
            Intent intent = new Intent(this, GameScene.class);
            startActivity(intent);
        });
        mediumBtn.setOnClickListener(v -> {
            // start next scene and store data
            SharedPreferences sharedPreferences = getSharedPreferences("storage", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("time", 20);
            editor.apply();
            Intent intent = new Intent(this, GameScene.class);
            startActivity(intent);
        });
        hardBtn.setOnClickListener(v -> {
            // start next scene and store data
            SharedPreferences sharedPreferences = getSharedPreferences("storage", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("time", 10);
            editor.apply();
            Intent intent = new Intent(this, GameScene.class);
            startActivity(intent);
        });
    }
}
