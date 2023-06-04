package com.u16.MobileMathsApp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GameScene extends AppCompatActivity {

    private TextView score;
    private TextView questionCount;
    private TextView time;
    private TextView question;
    private EditText input;
    private Button nextQuestion;

    private int setTime;
    private char operator;
    private int currentQuestionCount;
    private int scoreCount;
    private int num1;
    private int num2;

    private CountDownTimer countDownTimer;
    private final long TIMER_INTERVAL = 1; // 1 second
    private long TIMER_DURATION; // Convert to milliseconds

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        score = findViewById(R.id.score);
        questionCount = findViewById(R.id.total_questions);
        time = findViewById(R.id.time);
        question = findViewById(R.id.question);
        input = findViewById(R.id.input);
        nextQuestion = findViewById(R.id.next_question);

        // filling in some values
        operator = genRandomOperator();
        currentQuestionCount = 0;
        scoreCount = 0;
        num1 = genRandomNumber(operator);
        num2 = genRandomNumber(operator);
        TIMER_DURATION = getTime();

        final String[] newQuestion = {num1 + String.valueOf(operator) + num2};

        // Update all components for the first time
        updateTextViewUI();

        // Start the timer
        startTimer();

        nextQuestion.setOnClickListener(v -> {
            String answer = input.getText().toString();
            int expectedAnswer;
            switch (operator) {
                case '+':
                    expectedAnswer = num1 + num2;
                    break;
                case '-':
                    expectedAnswer = num1 - num2;
                    break;
                case '*':
                    expectedAnswer = num1 * num2;
                    break;
                default:
                    expectedAnswer = num1 / num2;
            }

            if (Integer.parseInt(answer) == expectedAnswer) { // checking answer to see if it's correct
                scoreCount++;
            }

            currentQuestionCount++;
            if (currentQuestionCount < 10) {
                operator = genRandomOperator();
                num1 = genRandomNumber(operator);
                num2 = genRandomNumber(operator);
                newQuestion[0] = num1 + String.valueOf(operator) + num2;
                updateTextViewUI();
                restartTimer(); // Restart the timer for the next question
            } else {
                // end of game, storing data and moving to next scene
                SharedPreferences sharedPreferences = getSharedPreferences("storage", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("score", scoreCount);
                editor.apply();

                Intent intent = new Intent(this, ScoreScene.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    // Updates time UI
    private void updateTime(int updatedTime) {
        time.setText("Time: " + updatedTime);
    }

    @SuppressLint("SetTextI18n")
    // Updates our UI
    private void updateTextViewUI() {
        questionCount.setText("Question: " + (currentQuestionCount + 1) + "/10");
        score.setText("Score: " + scoreCount + "/10");
        question.setText(String.valueOf(num1) + operator + num2);
        input.setText("");
    }

    // This function gets the time that each question will be displayed for
    private int getTime() {
        SharedPreferences sharedPreferences = getSharedPreferences("storage", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("time", 0);
    }

    // This function will generate a random operator for us
    private char genRandomOperator() {
        Random random = new Random();
        int x = random.nextInt(4);
        if (x == 0) {
            return '+';
        } else if (x == 1) {
            return '-';
        } else if (x == 2) {
            return '*';
        } else {
            return '/';
        }
    }

    // This function will generate a random number for us based on the operator
    private int genRandomNumber(char operator) {
        Random random = new Random();
        if (operator == '-' || operator == '/') {
            return random.nextInt(12);
        } else {
            // This will return a number between -12 and 12
            return random.nextInt(25) - 12;
        }
    }

    // Starts the timer with countdown duration and interval
    private void startTimer() {
        countDownTimer = new CountDownTimer(TIMER_DURATION * 1000, TIMER_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                int secondsRemaining = (int) (millisUntilFinished / 1000);
                updateTime(secondsRemaining);
            }

            @Override
            public void onFinish() {
                // Timer finished, handle accordingly
            }
        }.start();
    }

    // Stops the timer
    private void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    // Restarts the timer with initial settings
    private void restartTimer() {
        stopTimer();
        startTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
    }
}