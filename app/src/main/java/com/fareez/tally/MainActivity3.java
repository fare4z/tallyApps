package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity3 extends AppCompatActivity {

    TextView tvOutputNama,tvOutputFile;
    SharedPreferences sp;
    Button btnLogout, btnSimpan, btnDisplay;

    EditText etTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvOutputNama = findViewById(R.id.tvOutputNama);
        btnLogout = findViewById(R.id.btnLogout);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnDisplay = findViewById(R.id.btnDisplay);
        etTask = findViewById(R.id.etTask);
        tvOutputFile = findViewById(R.id.tvOutputFile);

        sp = getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);

        String output = sp.getString("nama","NULL");
        Boolean isLoggedIn = sp.getBoolean("isLoggedIn",false);

        if (!isLoggedIn) {
                Intent i = new Intent(getApplicationContext(),MainActivity2.class);
                i.putExtra("msg","Please enter your name");
                startActivity(i);
        }

        tvOutputNama.setText(output);


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.apply();

                Intent i = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(i);

            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String FILENAME = "HELLO_FILE";
                    FileOutputStream fos = openFileOutput(FILENAME,Context.MODE_PRIVATE);
                    fos.write(etTask.getText().toString().getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FILENAME = "HELLO_FILE";
                try {
                    FileInputStream fin = openFileInput(FILENAME);
                    int c;
                    String txtOutput="";

                    while (((c = fin.read())) != -1) {
                        txtOutput = txtOutput + Character.toString((char)c);
                     }
                    tvOutputFile.setText(txtOutput);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

}