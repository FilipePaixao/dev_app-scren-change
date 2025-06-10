package com.example.troca_tela;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edtDescricao, edtData, edtHora;
    Spinner spinnerPrioridade;
    DBHelper dbHelper; // Banco de dados SQLite
    int userId; // id do usuário logado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtDescricao = findViewById(R.id.edtDescricao);
        edtData = findViewById(R.id.edtData);
        edtHora = findViewById(R.id.edtHora);
        spinnerPrioridade = findViewById(R.id.spinnerPrioridade);

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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.opcoes_prioridade, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrioridade.setAdapter(adapter);
    }

    public void salvarTarefa(View view) {
        String desc = edtDescricao.getText().toString().trim();
        String data = edtData.getText().toString().trim();
        String hora = edtHora.getText().toString().trim();
        String prioridade = spinnerPrioridade.getSelectedItem().toString();

        if (userId == -1) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        if (desc.isEmpty() || data.isEmpty() || hora.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        Model tarefa = new Model(desc, data, hora, prioridade);

        dbHelper.inserirTarefa(tarefa, userId); // salva no banco

        Toast.makeText(this, "Tarefa salva no banco!", Toast.LENGTH_SHORT).show();

        // Limpar campos (opcional)
        edtDescricao.setText("");
        edtData.setText("");
        edtHora.setText("");
        spinnerPrioridade.setSelection(0);

        // Ir para tela de lista
        Intent intent = new Intent(this, ListaTarefasActivity.class);
        intent.putExtra("user_id", userId);
        startActivity(intent);
    }

    public void abrirLista(View view) {
        if (userId == -1) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        Intent intent = new Intent(this, ListaTarefasActivity.class);
        intent.putExtra("user_id", userId);
        startActivity(intent);
    }
}
