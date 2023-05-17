package com.example.projetoaluno.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetoaluno.database.LocalDatabase;
import com.example.projetoaluno.databinding.ActivityAlunoListBinding;
import com.example.projetoaluno.entities.Aluno;

import java.util.List;

public class AlunoList extends AppCompatActivity {
    private ActivityAlunoListBinding binding;
    private LocalDatabase db;
    private List<Aluno> alunoCursoList;
    private ListView listViewAluno;
    private Intent edtIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAlunoListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        listViewAluno = binding.listAluno;

        binding.btnHomeAluno.setOnClickListener(v -> finish());
        binding.btnAddAluno.setOnClickListener(v -> startActivity(new Intent(AlunoList.this, AlunoView.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        edtIntent = new Intent(this, AlunoView.class);
        preencheAlunos();
    }

    private void preencheAlunos() {
        alunoCursoList = db.alunoModel().getAll();
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunoCursoList);
        listViewAluno.setAdapter(adapter);

        listViewAluno.setOnItemClickListener((adapter1, view, position, id) -> {
            Aluno alunoSelecionado = alunoCursoList.get(position);
            edtIntent.putExtra("ALUNO_SELECIONADO_ID", alunoSelecionado.getId());
            startActivity(edtIntent);
        });
    }
}