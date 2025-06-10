package com.example.troca_tela;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "tarefas.db";
    private static final int DB_VERSION = 2; // incremented for user table

    private static final String TABLE_TAREFAS = "tarefas";
    private static final String TABLE_USUARIOS = "usuarios";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsuarios = "CREATE TABLE " + TABLE_USUARIOS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "usuario TEXT UNIQUE, senha TEXT)";
        db.execSQL(createUsuarios);

        String createTarefas = "CREATE TABLE " + TABLE_TAREFAS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_id INTEGER, descricao TEXT, data TEXT, hora TEXT, prioridade TEXT, " +
                "FOREIGN KEY(user_id) REFERENCES " + TABLE_USUARIOS + "(id))";
        db.execSQL(createTarefas);
    }

    // region Usuários
    public long inserirUsuario(String usuario, String senha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("usuario", usuario);
        cv.put("senha", senha);
        long id = db.insert(TABLE_USUARIOS, null, cv);
        db.close();
        return id;
    }

    public int autenticarUsuario(String usuario, String senha) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id FROM " + TABLE_USUARIOS + " WHERE usuario=? AND senha=?",
                new String[]{usuario, senha});
        int userId = -1;
        if (c.moveToFirst()) {
            userId = c.getInt(c.getColumnIndexOrThrow("id"));
        }
        c.close();
        db.close();
        return userId;
    }
    // endregion
    public void excluirTarefa(Model tarefa, int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TAREFAS,
                "user_id=? AND descricao=? AND data=? AND hora=? AND prioridade=?",
                new String[]{String.valueOf(userId), tarefa.getDescricao(), tarefa.getData(), tarefa.getHora(), tarefa.getPrioridade()});
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAREFAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        onCreate(db);
    }

    public void inserirTarefa(Model tarefa, int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("user_id", userId);
        cv.put("descricao", tarefa.getDescricao());
        cv.put("data", tarefa.getData());
        cv.put("hora", tarefa.getHora());
        cv.put("prioridade", tarefa.getPrioridade());
        db.insert(TABLE_TAREFAS, null, cv);
        db.close();
    }

    public ArrayList<Model> getTarefasUsuario(int userId) {
        ArrayList<Model> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT descricao, data, hora, prioridade FROM " + TABLE_TAREFAS + " WHERE user_id=?",
                new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {
            do {
                String desc = cursor.getString(cursor.getColumnIndexOrThrow("descricao"));
                String data = cursor.getString(cursor.getColumnIndexOrThrow("data"));
                String hora = cursor.getString(cursor.getColumnIndexOrThrow("hora"));
                String prioridade = cursor.getString(cursor.getColumnIndexOrThrow("prioridade"));

                lista.add(new Model(desc, data, hora, prioridade));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }
}
