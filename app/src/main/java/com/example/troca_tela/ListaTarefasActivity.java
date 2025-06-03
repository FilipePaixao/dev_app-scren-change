package com.example.troca_tela;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaTarefasActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TarefaAdapter adapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tarefas);

        recyclerView = findViewById(R.id.recyclerViewTarefas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DBHelper(this);
        ArrayList<Model> listaTarefas = dbHelper.getTodasTarefas();

        adapter = new TarefaAdapter(listaTarefas);
        recyclerView.setAdapter(adapter);

        Button btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(v -> finish());
    }
}
