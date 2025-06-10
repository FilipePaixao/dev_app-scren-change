package com.example.troca_tela;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText edtUsuario, edtSenha;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUsuario = findViewById(R.id.edtUsuarioCadastro);
        edtSenha = findViewById(R.id.edtSenhaCadastro);
        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        dbHelper = new DBHelper(this);

        btnRegistrar.setOnClickListener(v -> realizarCadastro());
    }

    private void realizarCadastro() {
        String usuario = edtUsuario.getText().toString().trim();
        String senha = edtSenha.getText().toString().trim();
        if (usuario.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha usuario e senha", Toast.LENGTH_SHORT).show();
            return;
        }
        long id = dbHelper.inserirUsuario(usuario, senha);
        if (id != -1) {
            Toast.makeText(this, "Cadastro realizado", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        } else {
            Toast.makeText(this, "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
        }
    }
}
