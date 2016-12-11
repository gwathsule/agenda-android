package com.example.rafael.agenda_contatos.view;

import android.app.DatePickerDialog;
import android.database.SQLException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.rafael.agenda_contatos.R;
import com.example.rafael.agenda_contatos.control.CtrContatos;
import com.example.rafael.agenda_contatos.model.Contato;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.Calendar;
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

        if (contato.getId() != 0)
            menu.getItem(1).setVisible(true);

        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.mn_acao1:
                    salvar();
                    finish();
                break;
            case R.id.mn_acao2:
                    excluir();
                    finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void excluir(){
        try{

            control.excluirContato(contato.getId());

        } catch (SQLException ex){
            info(ex.getMessage());
        } catch (Exception ex){
            info(ex.getMessage());
        }
    }

    private void preencherDadosContato(){
        txtNome.setText(contato.getNome());
        txtTelefone.setText(contato.getTelefone());
        txtEmail.setText(contato.getEmail());
        txtEndereço.setText(contato.getEndereco());

        DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String dt = format.format(contato.getDatasEspeciais());
        txtDatasEspeciais.setText(dt);

        txtGrupo.setText(contato.getGrupos());

        spnTipoTelefone.setSelection(Integer.parseInt(contato.getTipoTelefone()));
        spnTipoEmail.setSelection(Integer.parseInt(contato.getTipoEmail()));
        spnTipoEndereco.setSelection(Integer.parseInt(contato.getTipoEndereco()));
        spnTipoDatasEspeciais.setSelection(Integer.parseInt(contato.getTipoDatasEspeciais()));
    }

    private void salvar(){
        try {
            contato.setNome(txtNome.getText().toString());
            contato.setEmail(txtEmail.getText().toString());
            contato.setEndereco(txtEndereço.getText().toString());
            contato.setGrupos(txtGrupo.getText().toString());
            contato.setTelefone(txtTelefone.getText().toString());

            contato.setTipoTelefone(String.valueOf(spnTipoTelefone.getSelectedItemPosition()));
            contato.setTipoEndereco(String.valueOf(spnTipoEndereco.getSelectedItemPosition()));
            contato.setTipoDatasEspeciais(String.valueOf(spnTipoDatasEspeciais.getSelectedItemPosition()));
            contato.setTipoEmail(String.valueOf(spnTipoEmail.getSelectedItemPosition()));


            if(contato.getId() == 0){
                control.inserirContato(contato);
            } else {
                control.alterarContato(contato);
            }
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

        ExibeDataListener dateListener = new ExibeDataListener();
        txtDatasEspeciais.setOnClickListener(dateListener);
        txtDatasEspeciais.setOnFocusChangeListener(dateListener);

        Bundle bundle =  getIntent().getExtras();

        if((bundle != null ) && (bundle.containsKey("CONTATO"))){
            contato = (Contato) bundle.getSerializable("CONTATO");
            preencherDadosContato();
            //info("tem um contto aqui!: " + contato.getNome());
        } else {
            contato = new Contato();
            //info("nada de contatos");
        }

    }

    private void exibeData(){

        Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dlg = new DatePickerDialog(this, new SelecionaDataListener(), ano, mes, dia);
        dlg.show();
    }

    private class ExibeDataListener implements View.OnClickListener, View.OnFocusChangeListener{
        @Override
        public void onClick(View v) {
            exibeData();
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            exibeData();
        }
    }

    private class SelecionaDataListener implements DatePickerDialog.OnDateSetListener{

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);

            Date date = calendar.getTime();
            DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
            String data = format.format(date);
            txtDatasEspeciais.setText(data);
            contato.setDatasEspeciais(date);
        }
    }
}
