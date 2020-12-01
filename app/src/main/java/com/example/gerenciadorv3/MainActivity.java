package com.example.gerenciadorv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.gerenciadorv3.bdHelper.ColaboradoresBd;
import com.example.gerenciadorv3.model.Colaboradores;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    ColaboradoresBd bdHelper;
    ArrayList<Colaboradores> listColaboradores;
    Colaboradores colaborador;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.listColaboradores);
        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Colaboradores colaboradorEscolhido = (Colaboradores) adapter.getItemAtPosition(position);

                Intent i = new Intent(MainActivity.this, FormularioColaboradores.class);
                i.putExtra("colaborador-escolhido", colaboradorEscolhido);


            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                colaborador = (Colaboradores) adapter.getItemAtPosition(position);
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Deletar Colaborador");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                bdHelper = new ColaboradoresBd((MainActivity.this));
                bdHelper.deletarColaborador(colaborador);
                bdHelper.close();
                carregarColaborador();
                return true;
            }
        });

        Button btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormularioColaboradores.class);
                startActivity(intent);
            }
        });
    }

    protected void onResume(){
        super.onResume();
        carregarColaborador();
    }

    public void carregarColaborador(){
        bdHelper = new ColaboradoresBd(MainActivity.this);
        listColaboradores = bdHelper.getLista();
        bdHelper.close();

        if (listColaboradores != null) {
            adapter = new ArrayAdapter<Colaboradores>(MainActivity.this, android.R.layout.simple_list_item_1, listColaboradores);
            lista.setAdapter(adapter);
        }
        finish();
    }
}