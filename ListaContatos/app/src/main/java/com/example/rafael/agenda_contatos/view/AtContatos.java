package com.example.rafael.agenda_contatos.view;

import android.database.SQLException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.content.*;

import com.example.rafael.agenda_contatos.R;
import com.example.rafael.agenda_contatos.control.CtrContatos;
import com.example.rafael.agenda_contatos.dao.SQLite_DB;

public class AtContatos extends AppCompatActivity implements android.view.View.OnClickListener {

    private ImageButton btAdicionar;
    private EditText txtPesquisa;
    private ListView lstContatos;
    private CtrContatos control;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contatos);
        control = new CtrContatos(this);
        iniciarComponentes();
        iniciarBD();
    }

    public void iniciarBD(){

        //inicia o SQL Lite
        try {
            control.conexaoSQLite();
            AlertDialog.Builder spam = new AlertDialog.Builder(this);
            spam.setMessage("Conexão criada com sucesso");
            spam.setNeutralButton("OK", null);
            spam.show();
        } catch (SQLException ex){
            AlertDialog.Builder spam = new AlertDialog.Builder(this);
            spam.setMessage("Erro ao criar o banco: " + ex.getMessage());
            spam.setNeutralButton("OK", null);
            spam.show();
        }
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(this, AtNovoContato.class);
        startActivity(it);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void iniciarComponentes(){
        btAdicionar = (ImageButton) findViewById(R.id.btAdicionar);
        txtPesquisa = (EditText) findViewById(R.id.txtPesquisa);
        lstContatos = (ListView) findViewById(R.id.lstContatos);
        btAdicionar.setOnClickListener(this);
    }
}
