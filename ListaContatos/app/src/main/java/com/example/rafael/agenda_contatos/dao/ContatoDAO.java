package com.example.rafael.agenda_contatos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.example.rafael.agenda_contatos.model.Contato;

import java.util.Date;

/**
 * Created by Rafael on 27/11/2016.
 */

public class ContatoDAO {

    public ContatoDAO() {
    }

    public ArrayAdapter<Contato> buscaContatosSQLite(SQLiteDatabase conexao, Context context){

        ArrayAdapter<Contato> adpContatos = new ArrayAdapter<Contato>(context, android.R.layout.simple_list_item_1);

        Cursor cursor = conexao.query("CONTATO", null, null, null, null, null, null);

        if (cursor.getCount() > 0){

            cursor.moveToFirst();

            do {

                Contato contato = new Contato();

                contato.setNome(cursor.getString(1));
                contato.setTelefone(cursor.getString(2));
                contato.setTipoTelefone(cursor.getString(3));
                contato.setEmail(cursor.getString(4));
                contato.setTipoEmail(cursor.getString(5));
                contato.setEndereco(cursor.getString(6));
                contato.setTipoEndereco(cursor.getString(7));
                contato.setDatasEspeciais(new Date(cursor.getLong(8)));
                contato.setTipoDatasEspeciais(cursor.getString(9));
                contato.setGrupos(cursor.getString(10));

                adpContatos.add(contato);

            }while (cursor.moveToNext());
        }

        return adpContatos;
    }

    public void inserirContato(Contato contato, SQLiteDatabase conexao){
        ContentValues values = new ContentValues();

        values.put("NOME", contato.getNome());
        values.put("TELEFONE", contato.getTelefone());
        values.put("TIPOTELEFONE", contato.getTipoEndereco());
        values.put("EMAIL", contato.getEmail());
        values.put("TIPOEMAIL", contato.getTipoEmail());
        values.put("ENDERECO", contato.getEndereco());
        values.put("TIPOENDERECO", contato.getTipoEndereco());
        values.put("DATASESPECIAIS", contato.getDatasEspeciais().getTime());
        values.put("TIPODATASESPECIAIS", contato.getTipoDatasEspeciais());
        values.put("GRUPOS", contato.getGrupos());

        conexao.insertOrThrow("CONTATO", null, values);
    }
}
