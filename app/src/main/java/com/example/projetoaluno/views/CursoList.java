package com.example.projetoaluno.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetoaluno.database.LocalDatabase;
import com.example.projetoaluno.databinding.ActivityCursoListBinding;
import com.example.projetoaluno.entities.Curso;

import java.util.List;

public class CursoList extends AppCompatActivity {
    private ActivityCursoListBinding binding;
    private LocalDatabase db;
    private List<Curso> cursos;
    private ListView listViewCursos;
    private Intent edtIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCursoListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        listViewCursos = binding.listCursos;

        binding.btnHomeCurso.setOnClickListener(v -> finish());
        binding.btnAddCurso.setOnClickListener(v -> startActivity(new Intent(CursoList.this, CursoView.class)));
    }
    @Override
    protected void onResume() {
        super.onResume();
        edtIntent = new Intent(this, CursoView.class);
        preencheCursos();
    }
    private void preencheCursos() {
        cursos = db.cursoModel().getAll();
        ArrayAdapter<Curso> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cursos);
        listViewCursos.setAdapter(adapter);

        listViewCursos.setOnItemClickListener((adapter1, view, position, id) -> {
            Curso cursoSelecionada = cursos.get(position);
            edtIntent.putExtra("CURSO_SELECIONADA_ID",
                    cursoSelecionada.getId());
            startActivity(edtIntent);
        });
    }
}