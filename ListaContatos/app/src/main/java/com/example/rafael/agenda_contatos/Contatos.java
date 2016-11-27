package com.example.rafael.agenda_contatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.content.*;

public class Contatos extends AppCompatActivity implements android.view.View.OnClickListener {

    private ImageButton btAdicionar;
    private EditText txtPesquisa;
    private ListView lstContatos;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contatos);

        btAdicionar = (ImageButton) findViewById(R.id.btAdicionar);
        txtPesquisa = (EditText) findViewById(R.id.txtPesquisa);
        lstContatos = (ListView) findViewById(R.id.lstContatos);

        btAdicionar.setOnClickListener(this);//parei aos 43:32
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(this, NovoContato.class);
        startActivity(it);
    }
}
