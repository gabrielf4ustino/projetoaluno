package com.example.projetoaluno.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetoaluno.database.LocalDatabase;
import com.example.projetoaluno.databinding.ActivityAlunoViewBinding;
import com.example.projetoaluno.entities.Aluno;
import com.example.projetoaluno.entities.Curso;

import java.util.List;

public class AlunoView extends AppCompatActivity {

    private ActivityAlunoViewBinding binding;
    private LocalDatabase db;
    private int dbAlunoID;
    private Aluno dbAluno;
    private List<Curso> cursos;
    private Spinner spnCursos;
    private ArrayAdapter<Curso> cursosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAlunoViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = LocalDatabase.getDatabase(getApplicationContext());

        spnCursos = binding.spnMarcas;
        dbAlunoID = getIntent().getIntExtra(
                "CELULAR_SELECIONADO_ID", -1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(dbAlunoID >= 0){
            preencheAluno();
        } else {
            binding.btnExcluirModelo.setVisibility(View.GONE);
        }
        preencheMarcas();
    }

    private void preencheAluno() {
        dbAluno = db.alunoModel().getCel(dbAlunoID);
        binding.edtModelo.setText(dbAluno.getCursoID());
    }

    private void preencheMarcas() {
        cursos = db.cursoModel().getAll();
        cursosAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, cursos);
        spnCursos.setAdapter(cursosAdapter);
        if(dbAluno != null) {
            spnCursos.setSelection(dbAluno.getCursoID() - 1);
        }
    }

    public void salvarAluno(View view) {
        String modelo = binding.edtModelo.getText().toString();
        String novaMarca = "";

        if(spnCursos.getSelectedItem() != null){
            novaMarca = spnCursos.getSelectedItem().toString();
        }
        if(modelo.equals("")){
            Toast.makeText(this, "O modelo é obrigatório", Toast.LENGTH_SHORT).show();
            return;
        }
        if(novaMarca.equals("")) {
            Toast.makeText(this, "Entre com uma Marca.", Toast.LENGTH_SHORT).show();
            return;
        }

        Aluno novoAluno = new Aluno();
        novoAluno.setCurso(modelo);
        novoAluno.setCursoID(cursos.get(
                spnCursos.getSelectedItemPosition()).getCursoID());
        if(dbAluno != null){
            novoAluno.setId(dbAlunoID);
            db.alunoModel().update(novoAluno);
            Toast.makeText(this, "Aluno atualizado com sucesso.",
                    Toast.LENGTH_SHORT).show();
        } else {
            db.alunoModel().insertAll(novoAluno);
            Toast.makeText(this, "Aluno cadastrado com sucesso.",
                    Toast.LENGTH_SHORT).show();
        }
        finish();
    }
    public void excluirAluno(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Exclusão de Aluno")
                .setMessage("Deseja excluir esse celular?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluir();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void excluir() {
        db.alunoModel().delete(dbAluno);
        Toast.makeText(this, "Aluno excluído com sucesso.", Toast.LENGTH_SHORT).show();
        finish();
    }
    public void voltar(View view) {
        finish();
    }
}