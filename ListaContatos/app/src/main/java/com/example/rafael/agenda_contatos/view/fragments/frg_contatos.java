package com.example.rafael.agenda_contatos.view.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.rafael.agenda_contatos.R;
import com.example.rafael.agenda_contatos.model.Contato;
import com.example.rafael.agenda_contatos.view.AtNovoContato;

import java.text.DateFormat;

public class Frg_contatos extends Fragment {

    private int idContato;

    private EditText txtNome;
    private EditText txtTelefone;
    private EditText txtEmail;
    private EditText txtEndereço;
    private EditText txtDatasEspeciais;
    private EditText txtGrupo;

    /*----*/

    private TextView lnlTelefone;
    private TextView lnlEmail;
    private TextView lnlEndereço;
    private TextView lnlDatasEspeciais;

    /*----*/

    private Spinner spnTipoTelefone;
    private Spinner spnTipoEmail;
    private Spinner spnTipoEndereco;
    private Spinner spnTipoDatasEspeciais;

    private ArrayAdapter<String> adpTipoTelefone;
    private ArrayAdapter<String> adpTipoEmail;
    private ArrayAdapter<String> adpTipoEndereco;
    private ArrayAdapter<String> adpTipoDatasEspeciais;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frg_contatos, null);
        iniciarComponentes(view);
        return view;
    }

    public void preencherDadosContato(Contato contato){

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

        lnlTelefone.setText(spnTipoTelefone.getSelectedItem().toString());

    }

    private void iniciarComponentes(View view){
        txtNome = (EditText) view.findViewById(R.id.txtNome_fr);
        txtTelefone =  (EditText) view.findViewById(R.id.txtTelefone_fr);
        txtEmail = (EditText) view.findViewById(R.id.txtEmail_fr);
        txtEndereço = (EditText) view.findViewById(R.id.txtEndereco_fr);
        txtDatasEspeciais = (EditText) view.findViewById(R.id.txtDatasEspeciais_fr);
        txtGrupo = (EditText) view.findViewById(R.id.txtGrupo_fr);

        lnlTelefone =  (TextView) view.findViewById(R.id.lnl_telefone_fr);
        lnlEmail = (TextView) view.findViewById(R.id.lnl_email_fr);
        lnlEndereço = (TextView) view.findViewById(R.id.lnl_endereco_fr);
        lnlDatasEspeciais = (TextView) view.findViewById(R.id.lnl_datas_especiais_fr);

        spnTipoTelefone = (Spinner) view.findViewById(R.id.spnTipoTelefone_fr);
        spnTipoEmail = (Spinner) view.findViewById(R.id.spnTipoEmail_fr);
        spnTipoEndereco = (Spinner) view.findViewById(R.id.spnTipoEndereco_fr);
        spnTipoDatasEspeciais= (Spinner) view.findViewById(R.id.spnTipoDatasEspeciais_fr);

        //construtor dos spinner passando como referencia essa activity e setando o layout padrão "simple_spinner_item"
        adpTipoTelefone = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
        adpTipoTelefone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adpTipoEmail = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
        adpTipoEmail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adpTipoEndereco = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
        adpTipoEndereco.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adpTipoDatasEspeciais = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
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
