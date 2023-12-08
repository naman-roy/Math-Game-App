package com.company.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class Addition extends AppCompatActivity {

    TextView score;
    TextView life;
    TextView time;

    TextView question;
    EditText answer;

    Button ok;
    Button next;

    Random random= new Random();
    int num1;
    int num2;

    int userAnswer;
    int correctAnswer;
    int userScore=0;
    int userLife=3;
    CountDownTimer timer;
    private static final long START_TIMER_IN_MILIS=10000;
    Boolean timer_running;
    long time_left_in_milis=START_TIMER_IN_MILIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        score= findViewById(R.id.textViewScore);
        life= findViewById(R.id.textViewLife);
        time= findViewById(R.id.textViewTime);

        question= findViewById(R.id.textViewQuestion);
        answer=findViewById(R.id.editTextAnswer);

        ok= findViewById(R.id.buttonOk);
        next=findViewById(R.id.buttonNext);

        gameContinue();

        ok.setOnClickListener(view -> {

            userAnswer= Integer.parseInt(answer.getText().toString());
            pauseTimer();

            if (userAnswer==correctAnswer){
                userScore+=10;
                score.setText(""+userScore);
                question.setText("Great Job!, That's the Correct Answer");
            }
            else {
                userLife-=1;
                life.setText(""+userLife);
                question.setText("Oops!, Wrong Answer");
            }
        });

        next.setOnClickListener(view -> {

            answer.setText("");
            resetTimer();
            gameContinue();

            if (userLife==0){

                Toast.makeText(getApplicationContext(),"Game Over!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(Addition.this,Result.class);
                intent.putExtra("score", userScore);
                startActivity(intent);
                finish();

            }
            else {

                gameContinue();

            }


        });
    }

    //Game Logic
    public void gameContinue(){
        num1=random.nextInt(150);
        num2= random.nextInt(100);
        correctAnswer=num1+num2;

        question.setText(String.format(String.format("%d + %d", num1, num2)));
        startTimer();
    }

    //Game Timer
    public  void startTimer(){
        timer= new CountDownTimer(time_left_in_milis,60000) {
            @Override
            public void onTick(long l) {
                time_left_in_milis=l;
                updateText();

            }

            @Override
            public void onFinish() {

                timer_running=false;
                pauseTimer();
                resetTimer();
                updateText();
                userLife= userLife-1;
                life.setText(""+userLife);
                question.setText("Sorry! Time's Up");

            }
        }.start();
        timer_running=true;
    }
    public  void  updateText(){

        int second = (int)(time_left_in_milis/1000)%60;
        String time_left= String.format(Locale.getDefault(), "%02d",second);
        time.setText(time_left);
    }

    public  void pauseTimer(){

        timer.cancel();
        timer_running=false;

    }

    public  void resetTimer(){

        time_left_in_milis=START_TIMER_IN_MILIS;
        updateText();
    }
}






