package com.example.rafael.agenda_contatos.view;

import android.database.SQLException;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.content.*;

import com.example.rafael.agenda_contatos.R;
import com.example.rafael.agenda_contatos.control.CtrContatos;
import com.example.rafael.agenda_contatos.dao.SQLite_DB;
import com.example.rafael.agenda_contatos.model.Contato;

import java.io.Serializable;

public class AtContatos extends AppCompatActivity implements android.view.View.OnClickListener {

    private ImageButton btAdicionar;
    private EditText txtPesquisa;
    private ListView lstContatos;
    private transient CtrContatos control;
    private ArrayAdapter<Contato>  adpContatos;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contatos);
        control = new CtrContatos(this);
        iniciarComponentes();
        iniciarBD();
        adpContatos = control.getListaContatos();
        lstContatos.setAdapter(adpContatos);
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(this, AtNovoContato.class);

        startActivityForResult(it, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        adpContatos = control.getListaContatos();
        lstContatos.setAdapter(adpContatos);
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

    public void iniciarBD(){

        //inicia o SQL Lite
        try {
            control.inicarConexaoSQLite();
        } catch (SQLException ex){
            AlertDialog.Builder spam = new AlertDialog.Builder(this);
            spam.setMessage("Erro ao criar o banco: " + ex.getMessage());
            spam.setNeutralButton("OK", null);
            spam.show();
        }
    }

}
