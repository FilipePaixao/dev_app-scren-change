package com.example.troca_tela;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.ViewHolder> {
    private ArrayList<Model> tarefas;

    public TarefaAdapter(ArrayList<Model> tarefas) {
        this.tarefas = tarefas;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDescricao, txtDataHora, txtPrioridade;

        public ViewHolder(View view) {
            super(view);
            txtDescricao = view.findViewById(R.id.txtDescricao);
            txtDataHora = view.findViewById(R.id.txtDataHora);
            txtPrioridade = view.findViewById(R.id.txtPrioridade);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarefa, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Model tarefa = tarefas.get(position);
        holder.txtDescricao.setText(tarefa.getDescricao());
        holder.txtDataHora.setText("Data: " + tarefa.getData() + "  Hora: " + tarefa.getHora());
        holder.txtPrioridade.setText("Prioridade: " + tarefa.getPrioridade());

    }

    @Override
    public int getItemCount() {
        return tarefas.size();
    }
}
