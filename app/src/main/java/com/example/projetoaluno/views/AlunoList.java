package com.example.projetoaluno.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetoaluno.database.LocalDatabase;
import com.example.projetoaluno.databinding.ActivityAlunoListBinding;
import com.example.projetoaluno.entities.AlunoCurso;

import java.util.List;

public class AlunoList extends AppCompatActivity {
    private ActivityAlunoListBinding binding;
    private LocalDatabase db;
    private List<AlunoCurso> alunoCursoList;
    private ListView listViewAluno;
    private Intent edtIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAlunoListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        listViewAluno = binding.listCelular;

        binding.btnHomeCel.setOnClickListener(v -> finish());
        binding.btnAddCel.setOnClickListener(v -> startActivity(new Intent(AlunoList.this, AlunoView.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        edtIntent = new Intent(this, AlunoView.class);
        preencheCelulares();
    }

    private void preencheCelulares() {
        alunoCursoList = db.alunoCursoModel().getAllCelMarca();
        ArrayAdapter<AlunoCurso> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, alunoCursoList);
        listViewAluno.setAdapter(adapter);

        listViewAluno.setOnItemClickListener((adapter1, view, position, id) -> {
            AlunoCurso celSelecionado = alunoCursoList.get(position);
            edtIntent.putExtra("CELULAR_SELECIONADO_ID",
                    celSelecionado.getAlunoID());
            startActivity(edtIntent);
        });
    }
}