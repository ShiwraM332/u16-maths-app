package com.u16.MobileMathsApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MenuScene extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        Button nextBtn = findViewById(R.id.next_btn);

        nextBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, DifficultyScene.class);
            startActivity(intent);
        });
    }
}