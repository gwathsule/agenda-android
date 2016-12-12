package com.example.rafael.agenda_contatos.view.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rafael.agenda_contatos.R;
import com.example.rafael.agenda_contatos.util.Localizador;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaFragment extends SupportMapFragment {
    String endereco;
    // Evento chamado após a criação do fragment
    @Override
    public void onResume() {
        super.onResume();
        endereco = getActivity().getIntent().getExtras().getString("endereco");
        setContatoOnMap(endereco);
    }

    private void centralizarNo(LatLng latLng, GoogleMap mapa) {
        // 11 é o zoom da camera
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));
    }

    private void setContatoOnMap(String endereco){
        GoogleMap mapa = getMap();

        // Crio um maker para setar as opções que aparecerão no mapa
        MarkerOptions opcoes = new MarkerOptions();
        opcoes.title("Localização");
        opcoes.snippet(endereco);
        // Icone usado no mapa
        Bitmap bm;
        bm = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.icone_mapa);

        Bitmap bm_reduzido = Bitmap.createScaledBitmap(bm, 50, 50, true);
        // Setando a imagem como ícone a ser usado no mapa
        opcoes.icon(BitmapDescriptorFactory.fromBitmap(bm_reduzido));
        // Craindo um localizador
        Localizador local = new Localizador(getActivity());
        // Obtendo as coordenadas de um aluno a partid do seu endereço
        LatLng latLng = local.getCoordenada(endereco);
        // Se não for nulo (endereço encontrado), adiciona no map
        if (latLng != null) {
            opcoes.position(latLng);
            mapa.addMarker(opcoes);
        }
    }


}
