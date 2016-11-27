package com.example.rafael.agenda_contatos.control;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;


import com.example.rafael.agenda_contatos.dao.ContatoDAO;
import com.example.rafael.agenda_contatos.dao.SQLite_DB;
import com.example.rafael.agenda_contatos.view.AtContatos;

/**
 * Created by Rafael on 27/11/2016.
 */

public class CtrContatos {

    private SQLite_DB sqlite_db;
    private SQLiteDatabase sqlite_conexao;
    private AtContatos atContatos;
    private ContatoDAO contatoDAO;

    public CtrContatos(AtContatos atContatos) {
        this.atContatos = atContatos;
        this.contatoDAO = new ContatoDAO();
    }

    public void inicarConexaoSQLite() throws SQLException{
        sqlite_db = new SQLite_DB(atContatos);
        sqlite_conexao = sqlite_db.getWritableDatabase();

        //teste
        for(int i=0; i<10; i++) {
            ContentValues c = new ContentValues();
            c.put("TELEFONE", "123456789");
            sqlite_conexao.insertOrThrow("CONTATO", null, c);
        }
        //
    }

    public ArrayAdapter<String> getListaContatos(){
        return contatoDAO.buscaContatosSQLite(sqlite_conexao, atContatos);
    }
}
