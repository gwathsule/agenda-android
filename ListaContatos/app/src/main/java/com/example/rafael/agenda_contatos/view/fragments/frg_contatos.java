package com.example.rafael.agenda_contatos.view.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rafael.agenda_contatos.R;

public class Frg_contatos extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frg_contatos, null);
        TextView txt = (TextView) view.findViewById(R.id.txt1);
        txt.setText("Fragment 1");
        return view;
    }

    public void alteraTextView(String texto){
        TextView txt = (TextView) getView().findViewById(R.id.txt1);
        txt.setText(texto);
    }
}
