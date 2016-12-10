package com.example.rafael.agenda_contatos.view;

import android.database.SQLException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.rafael.agenda_contatos.R;
import com.example.rafael.agenda_contatos.control.CtrContatos;
import com.example.rafael.agenda_contatos.model.Contato;

import java.lang.reflect.Array;
import java.util.Date;

public class AtNovoContato extends AppCompatActivity {

    private EditText txtNome;
    private EditText txtTelefone;
    private EditText txtEmail;
    private EditText txtEndereço;
    private EditText txtDatasEspeciais;
    private EditText txtGrupo;

    private Spinner spnTipoTelefone;
    private Spinner spnTipoEmail;
    private Spinner spnTipoEndereco;
    private Spinner spnTipoDatasEspeciais;

    private ArrayAdapter<String> adpTipoTelefone;
    private ArrayAdapter<String> adpTipoEmail;
    private ArrayAdapter<String> adpTipoEndereco;
    private ArrayAdapter<String> adpTipoDatasEspeciais;

    private transient CtrContatos control;
    Contato contato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novo_contato);
        control = new CtrContatos(this);
        iniciarComponentes();
        iniciarBD();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*getMenuInflater().inflate(R.menu.menu, menu);
        return true; // codigo que o alexandre me enviou*/
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.mn_acao1:
                    if(contato == null){
                        inserir();
                    } else {

                    }
                    finish();
                break;
            case R.id.mn_acao2:

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void inserir(){

        try {
            contato = new Contato();

            contato.setNome(txtNome.getText().toString());
            contato.setDatasEspeciais(new Date());
            contato.setEmail(txtEmail.getText().toString());
            contato.setEndereco(txtEndereço.getText().toString());
            contato.setGrupos(txtGrupo.getText().toString());
            contato.setTelefone(txtTelefone.getText().toString());

            control.inserirContato(contato);
        } catch (SQLException ex){
            info(ex.getMessage());
        } catch (Exception ex){
            info(ex.getMessage());
        }
    }

    private void info(String info){
        AlertDialog.Builder spam = new AlertDialog.Builder(this);
        spam.setMessage(info);
        spam.setNeutralButton("OK", null);
        spam.show();
    }

    public void iniciarBD(){

        //inicia o SQL Lite
        try {
            control.inicarConexaoSQLite();
        } catch (SQLException ex){
            info("Erro na criação do banco: " + ex.getMessage());
        }
    }

    private void iniciarComponentes(){
        txtNome = (EditText) findViewById(R.id.txtNome);
        txtTelefone = (EditText) findViewById(R.id.txtTelefone);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtEndereço = (EditText) findViewById(R.id.txtEndereco);
        txtDatasEspeciais = (EditText) findViewById(R.id.txtDatasEspeciais);
        txtGrupo = (EditText) findViewById(R.id.txtGrupo);

        spnTipoTelefone = (Spinner) findViewById(R.id.spnTipoTelefone);
        spnTipoEmail = (Spinner) findViewById(R.id.spnTipoEmail);
        spnTipoEndereco = (Spinner) findViewById(R.id.spnTipoEndereco);
        spnTipoDatasEspeciais= (Spinner) findViewById(R.id.spnTipoDatasEspeciais);

        //construtor dos spinner passando como referencia essa activity e setando o layout padrão "simple_spinner_item"
        adpTipoTelefone = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpTipoTelefone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adpTipoEmail = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpTipoEmail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adpTipoEndereco = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpTipoEndereco.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adpTipoDatasEspeciais = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpTipoDatasEspeciais.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //associa cada objeto do tipo spinner à um objet do tipo array adapter
        spnTipoTelefone.setAdapter(adpTipoTelefone);
        spnTipoEmail.setAdapter(adpTipoEmail);
        spnTipoEndereco.setAdapter(adpTipoEndereco);
        spnTipoDatasEspeciais.setAdapter(adpTipoDatasEspeciais);

        //adiciona itens no menu do spinner para cada spinner;
        adpTipoTelefone.add("Celular");
        adpTipoTelefone.add("Casa");
        adpTipoTelefone.add("Trabalho");
        adpTipoTelefone.add("Outros");

        adpTipoEmail.add("Pessoal");
        adpTipoEmail.add("Trabalho");
        adpTipoEmail.add("Outros");

        adpTipoEndereco.add("Casa");
        adpTipoEndereco.add("Trabalho");
        adpTipoEndereco.add("Outros");

        adpTipoDatasEspeciais.add("Aniversário");
        adpTipoDatasEspeciais.add("Morte");
        adpTipoDatasEspeciais.add("Outros");
    }
}
