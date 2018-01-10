package com.example.projetodoscria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements View.OnClickListener {

    Button buttonEntrar;
    EditText editTextLogin, editTextSenha;

    String LOGIN_PADRAO = "admin";
    String SENHA_PADRAO = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonEntrar = (Button) findViewById(R.id.buttonEntrar);

        editTextLogin = (EditText) findViewById(R.id.editTextLogin);
        editTextSenha = (EditText) findViewById(R.id.editTextSenha);

        buttonEntrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == buttonEntrar.getId()) {
            if (editTextLogin.getText().toString().equals(LOGIN_PADRAO)) {
                if (editTextSenha.getText().toString().equals(SENHA_PADRAO)) {
                    startActivity(new Intent(this, MenuActivity.class));
                } else {
                    Toast.makeText(this, "SENHA INCORRETA", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "LOGIN INCORRETO", Toast.LENGTH_LONG).show();
            }
        }

    }
}
