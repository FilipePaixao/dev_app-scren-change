package com.example.troca_tela;

import android.content.Intent;
import android.content.SharedPreferences;
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
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tarefas);

        recyclerView = findViewById(R.id.recyclerViewTarefas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DBHelper(this);
        userId = getIntent().getIntExtra("user_id", -1);
        if (userId == -1) {
            SharedPreferences prefs = getSharedPreferences("app", MODE_PRIVATE);
            userId = prefs.getInt("user_id", -1);
        }
        if (userId == -1) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        ArrayList<Model> listaTarefas = dbHelper.getTarefasUsuario(userId);

        adapter = new TarefaAdapter(listaTarefas);
        recyclerView.setAdapter(adapter);

        Button btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(v -> finish());
    }
}
