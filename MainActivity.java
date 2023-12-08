package com.company.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    Button add;
    Button subs;
    Button multi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout=findViewById(R.id.linearLayout);
        add=findViewById(R.id.buttonAdd);
        subs=findViewById(R.id.buttonSub);
        multi=findViewById(R.id.buttonMul);

        //Addition
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,Addition.class);
                startActivity(intent);
                finish();
            }
        });

        //Subtraction
        subs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, Substraction.class);
                startActivity(intent);
                finish();
            }
        });

        //Multiplication
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, Multiplication.class);
                startActivity(intent);
                finish();
            }
        });
    }
}