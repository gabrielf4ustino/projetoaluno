package com.example.projetoaluno.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    private ListView listViewMarcas;
    private Intent edtIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCursoListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        listViewMarcas = binding.listMarcas;

        binding.btnHomeMarca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CursoList.this, CursoView.class));
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        edtIntent = new Intent(this, CursoView.class);
        preencheMarcas();
    }
    private void preencheMarcas() {
        cursos = db.cursoModel().getAll();
        ArrayAdapter<Curso> marcasAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, cursos);
        listViewMarcas.setAdapter(marcasAdapter);

        listViewMarcas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Curso marcaselecionada = cursos.get(position);
                edtIntent.putExtra("MARCA_SELECIONADA_ID",
                        marcaselecionada.getCursoID());
                startActivity(edtIntent);
            }
        });
    }
}