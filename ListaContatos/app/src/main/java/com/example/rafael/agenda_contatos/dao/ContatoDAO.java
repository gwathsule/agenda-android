package com.example.rafael.agenda_contatos.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

/**
 * Created by Rafael on 27/11/2016.
 */

public class ContatoDAO {

    public ContatoDAO() {
    }

    public ArrayAdapter<String> buscaContatosSQLite(SQLiteDatabase conexao, Context context){
        ArrayAdapter<String> adpContatos = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1);

        Cursor cursor = conexao.query("CONTATO", null, null, null, null, null, null);

        if (cursor.getCount() > 0){

            cursor.moveToFirst();

            do {

                String telefone = cursor.getString(1);
                adpContatos.add(telefone);

            }while (cursor.moveToNext());
        }

        return adpContatos;
    }
}
