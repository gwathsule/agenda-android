package com.example.rafael.agenda_contatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.content.*;

public class Contatos extends AppCompatActivity implements android.view.View.OnClickListener {

    private ImageButton btAdicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contatos);

        btAdicionar = (ImageButton) findViewById(R.id.btAdicionar);
        btAdicionar.setOnClickListener(this);//parei aos 43:32
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(this, NovoCliente.class);
        startActivity(it);
    }
}
