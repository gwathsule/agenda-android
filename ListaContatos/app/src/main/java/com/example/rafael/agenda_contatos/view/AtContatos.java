package com.example.rafael.agenda_contatos.view;

import android.content.res.Configuration;
import android.database.SQLException;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
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
import com.example.rafael.agenda_contatos.view.fragments.Frg_contatos;

import java.io.Serializable;

import static android.R.attr.fragment;

public class AtContatos extends FragmentActivity implements android.view.View.OnClickListener, AdapterView.OnItemClickListener {

    FragmentManager fm = getSupportFragmentManager();

    private ImageButton btAdicionar;
    private ListView lstContatos;
    private transient CtrContatos control;
    private ArrayAdapter<Contato>  adpContatos;
    private LinearLayout lnl_lista;
    Frg_contatos frg_contatos;
    Configuration config;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contatos);
        control = new CtrContatos(this);
        iniciarComponentes();
        iniciarBD();
        adpContatos = control.getListaContatos();
        lstContatos.setAdapter(adpContatos);
        lstContatos.setOnItemClickListener(this);
        if (!(config.orientation == Configuration.ORIENTATION_LANDSCAPE)){//Cel deitado
            frg_contatos.getView().setVisibility(View.GONE);
            lnl_lista.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        }
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Contato contato = adpContatos.getItem(position);

        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE){//em pé
            frg_contatos.preencherDadosContato(contato);
        }else{//deitado
            Intent it = new Intent(this, AtNovoContato.class);
            it.putExtra("CONTATO", contato);
            startActivityForResult(it, 0);
        }
    }

    private void iniciarComponentes(){
        btAdicionar = (ImageButton) findViewById(R.id.btAdicionar);
        lstContatos = (ListView) findViewById(R.id.lstContatos);
        btAdicionar.setOnClickListener(this);
        config = getResources().getConfiguration();
        frg_contatos = (Frg_contatos) fm.findFragmentById(R.id.fragment);
        lnl_lista = (LinearLayout) findViewById(R.id.lnl_lista);
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

    private void info(String info){
        AlertDialog.Builder spam = new AlertDialog.Builder(this);
        spam.setMessage(info);
        spam.setNeutralButton("OK", null);
        spam.show();
    }
}
