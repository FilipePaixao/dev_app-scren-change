package com.example.troca_tela;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtDescricao, edtData, edtHora;
    Spinner spinnerPrioridade;

    public static ArrayList<Model> listaTarefas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtDescricao = findViewById(R.id.edtDescricao);
        edtData = findViewById(R.id.edtData);
        edtHora = findViewById(R.id.edtHora);
        spinnerPrioridade = findViewById(R.id.spinnerPrioridade);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.opcoes_prioridade, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrioridade.setAdapter(adapter);
    }

    public void salvarTarefa(View view) {
        String desc = edtDescricao.getText().toString();
        String data = edtData.getText().toString();
        String hora = edtHora.getText().toString();
        String prioridade = spinnerPrioridade.getSelectedItem().toString();

        Model tarefa = new Model(desc, data, hora, prioridade);
        listaTarefas.add(tarefa);

        Toast.makeText(this, "Tarefa salva!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ListaTarefasActivity.class);
        startActivity(intent);
    }
}
