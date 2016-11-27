package com.example.rafael.agenda_contatos.control;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.rafael.agenda_contatos.dao.SQLite_DB;
import com.example.rafael.agenda_contatos.view.AtContatos;

/**
 * Created by Rafael on 27/11/2016.
 */

public class CtrContatos {

    private SQLite_DB sqlite_db;
    private SQLiteDatabase sqlite_conexao;
    private AtContatos  atContatos;

    public CtrContatos(AtContatos atContatos) {
        this.atContatos = atContatos;
    }

    public void conexaoSQLite() throws SQLException{
        sqlite_db = new SQLite_DB(atContatos);
        sqlite_conexao = sqlite_db.getReadableDatabase();
    }
}
