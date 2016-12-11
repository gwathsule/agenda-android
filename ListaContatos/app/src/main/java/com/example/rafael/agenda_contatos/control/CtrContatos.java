package com.example.rafael.agenda_contatos.control;

import android.app.Activity;
import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;


import com.example.rafael.agenda_contatos.dao.ContatoDAO;
import com.example.rafael.agenda_contatos.dao.SQLite_DB;
import com.example.rafael.agenda_contatos.model.Contato;
import com.example.rafael.agenda_contatos.view.AtContatos;

/**
 * Created by Rafael on 27/11/2016.
 */

public class CtrContatos {

    private SQLite_DB sqlite_db;
    private SQLiteDatabase sqlite_conexao;
    private Activity activity;
    private ContatoDAO contatoDAO;

    public CtrContatos(Activity activity) {
        this.activity = activity;
        this.contatoDAO = new ContatoDAO();
    }

    public void inicarConexaoSQLite() throws SQLException{
        this.sqlite_db = new SQLite_DB(activity);
        this.sqlite_conexao = sqlite_db.getWritableDatabase();
    }

    public ArrayAdapter<Contato> getListaContatos() {
        return contatoDAO.buscaContatosSQLite(sqlite_conexao, activity);
    }

    public void inserirContato(Contato contato){
        contatoDAO.inserirContato(contato, sqlite_conexao);
    }

    public void alterarContato(Contato contato){
        contatoDAO.alterarContato(contato, sqlite_conexao);
    }

    public void excluirContato(long id){
        contatoDAO.excluirContato(id, sqlite_conexao);
    }
}
