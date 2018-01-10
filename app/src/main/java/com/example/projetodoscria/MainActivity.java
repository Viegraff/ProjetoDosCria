package com.example.projetodoscria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this, "SOU FODA", Toast.LENGTH_LONG).show();

<<<<<<< HEAD
        Toast.makeText(MainActivity.this, "SOU FODA, NA CAMA EU TE ESCULACHO!", Toast.LENGTH_LONG).show();
=======
        Toast.makeText(MainActivity.this, "XAMPSON", Toast.LENGTH_LONG).show();
>>>>>>> ec5f98c4daab021c50c708fe547656aa46d56624
    }
}
