package com.example.troca_tela;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListaTarefasActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TarefaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tarefas);

        recyclerView = findViewById(R.id.recyclerViewTarefas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TarefaAdapter(MainActivity.listaTarefas);
        recyclerView.setAdapter(adapter);
    }
}
