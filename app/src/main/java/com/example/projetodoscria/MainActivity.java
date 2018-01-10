package com.example.projetodoscria;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this, "SOU FODA", Toast.LENGTH_LONG).show();

        Toast.makeText(MainActivity.this, "SOU FODA, NA CAMA EU TE ESCULACHO!", Toast.LENGTH_LONG).show();

        Toast.makeText(MainActivity.this, "XAMPSON", Toast.LENGTH_LONG).show();
    }
}
