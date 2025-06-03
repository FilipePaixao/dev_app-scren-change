package com.example.troca_tela;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "tarefas.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "tarefas";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "descricao TEXT, data TEXT, hora TEXT, prioridade TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void inserirTarefa(Model tarefa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("descricao", tarefa.getDescricao());
        cv.put("data", tarefa.getData());
        cv.put("hora", tarefa.getHora());
        cv.put("prioridade", tarefa.getPrioridade());
        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    public ArrayList<Model> getTodasTarefas() {
        ArrayList<Model> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

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
