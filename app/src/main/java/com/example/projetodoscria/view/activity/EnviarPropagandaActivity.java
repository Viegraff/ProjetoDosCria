package com.example.projetodoscria.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.projetodoscria.R;
import com.example.projetodoscria.view.fragment.EtapaFragment;
import com.example.projetodoscria.view.fragment.MapaFragment;

public class EnviarPropagandaActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_propaganda);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.frameLayoutMapa, new MapaFragment());
        fragmentTransaction.replace(R.id.frameLayoutEtapas, new EtapaFragment());

        fragmentTransaction.commit();

    }

}