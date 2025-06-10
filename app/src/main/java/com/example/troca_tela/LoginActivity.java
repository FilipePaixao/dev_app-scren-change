package com.example.troca_tela;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsuario, edtSenha;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsuario = findViewById(R.id.edtUsuario);
        edtSenha = findViewById(R.id.edtSenha);
        Button btnEntrar = findViewById(R.id.btnEntrar);
        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        dbHelper = new DBHelper(this);

        btnEntrar.setOnClickListener(v -> realizarLogin());
        btnCadastrar.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));
    }

    private void realizarLogin() {
        String usuario = edtUsuario.getText().toString().trim();
        String senha = edtSenha.getText().toString().trim();
        int id = dbHelper.autenticarUsuario(usuario, senha);
        if (id != -1) {
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("user_id", id);
            startActivity(i);
            finish();
        } else {
            Toast.makeText(this, "Credenciais incorretas", Toast.LENGTH_SHORT).show();
        }
    }
}
