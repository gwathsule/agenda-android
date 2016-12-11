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

                contato.setId(cursor.getLong(cursor.getColumnIndex(contato.ID)));
                contato.setNome(cursor.getString(cursor.getColumnIndex(contato.NOME)));
                contato.setTelefone(cursor.getString(cursor.getColumnIndex(contato.TELEFONE)));
                contato.setTipoTelefone(cursor.getString(cursor.getColumnIndex(contato.TIPOTELEFONE)));
                contato.setEmail(cursor.getString(cursor.getColumnIndex(contato.EMAIL)));
                contato.setTipoEmail(cursor.getString(cursor.getColumnIndex(contato.TIPOEMAIL)));
                contato.setEndereco(cursor.getString(cursor.getColumnIndex(contato.ENDERECO)));
                contato.setTipoEndereco(cursor.getString(cursor.getColumnIndex(contato.TIPOENDERECO)));
                contato.setDatasEspeciais(new Date(cursor.getLong(cursor.getColumnIndex(contato.DATASESPECIAIS))));
                contato.setTipoDatasEspeciais(cursor.getString(cursor.getColumnIndex(contato.TIPODATASESPECIAIS)));
                contato.setGrupos(cursor.getString(cursor.getColumnIndex(contato.GRUPOS)));

                adpContatos.add(contato);

            }while (cursor.moveToNext());
        }

        return adpContatos;
    }

    private ContentValues preencheContentValues(Contato contato){
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

        return values;
    }

    public void inserirContato(Contato contato, SQLiteDatabase conexao){
        ContentValues values = preencheContentValues(contato);
        conexao.insertOrThrow("CONTATO", null, values);
    }

    public void alterarContato(Contato contato, SQLiteDatabase conexao){
        ContentValues values = preencheContentValues(contato);
        conexao.update("CONTATO", values, " _id = ?", new String[]{String.valueOf(contato.getId())});
    }

    public void excluirContato(long id, SQLiteDatabase conexao){
        conexao.delete("CONTATO",  " _id = ?", new String[]{String.valueOf(id)});
    }
}
