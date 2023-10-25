package com.fareez.tally;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvOutput;
    Button btnCount,btnReset;
    Integer Counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOutput = findViewById(R.id.tvOutput);
        btnCount = findViewById(R.id.btnCount);
        btnReset = findViewById(R.id.btnReset);
        Counter = 0;

        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Counter++;
                tvOutput.setText(Counter.toString());
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvOutput.setText("0");
                Counter = 0;
            }
        });


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("key_counter", Counter);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle outState) {
        super.onRestoreInstanceState(outState);
        Counter = outState.getInt("key_counter",0);
        tvOutput.setText("" + Counter);
    }
}