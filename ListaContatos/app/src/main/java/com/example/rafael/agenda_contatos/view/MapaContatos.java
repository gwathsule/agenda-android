package com.example.rafael.agenda_contatos.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.rafael.agenda_contatos.R;
import com.example.rafael.agenda_contatos.util.VerificaConexaoWeb;
import com.example.rafael.agenda_contatos.view.fragments.MapaFragment;

public class MapaContatos extends FragmentActivity {

    private String endereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa_contatos);
        endereco = getIntent().getStringExtra("endereco");

        // Classe criada para verificar se há conexão com a web
        VerificaConexaoWeb conexaoWeb = new VerificaConexaoWeb(this);
        // Se houver, chama o fragment de mapas para preencher a tela
        if (conexaoWeb.verificaConexao()){
            // Criando o Fragment e passando a turma como parâmetro
            MapaFragment mapaFragment = new MapaFragment();
            // Passando a turma como parâmetro para o fragment de mapas
            Bundle argumentos = new Bundle();
            argumentos.putSerializable("endereco", endereco);
            mapaFragment.setArguments(argumentos);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.mapa_contatos, mapaFragment);
            fragmentTransaction.commit();
        } else {
            Toast.makeText(this, "Conexão de internet DESLIGADA", Toast.LENGTH_LONG).show();
        }

    }

}
