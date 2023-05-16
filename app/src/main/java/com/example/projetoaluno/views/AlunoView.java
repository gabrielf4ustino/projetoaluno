package com.example.projetoaluno.views;

import android.app.AlertDialog;
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

        spnCursos = binding.spnCursos;
        dbAlunoID = getIntent().getIntExtra("ALUNO_SELECIONADO_ID", -1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dbAlunoID >= 0) {
            preencheAluno();
        } else {
            binding.btnExcluirAluno.setVisibility(View.GONE);
        }
        preencheCursos();
    }

    private void preencheAluno() {
        dbAluno = db.alunoModel().getAlunoById(dbAlunoID);
        binding.edtNome.setText(dbAluno.getNome());
        binding.edtEmail.setText(dbAluno.getEmail());
        binding.edtTelefone.setText(dbAluno.getTelefone());
    }

    private void preencheCursos() {
        cursos = db.cursoModel().getAll();
        cursosAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cursos);
        spnCursos.setAdapter(cursosAdapter);
        if (dbAluno != null) {
            spnCursos.setSelection(dbAluno.getCursoID() - 1);
        }
    }

    public void salvarAluno(View view) {
        String nome = binding.edtNome.getText().toString();
        String email = binding.edtEmail.getText().toString();
        String telefone = binding.edtTelefone.getText().toString();
        String novoCurso = "";

        if (spnCursos.getSelectedItem() != null) {
            novoCurso = spnCursos.getSelectedItem().toString();
        }
        if (nome.equals("") || email.equals("") || telefone.equals("") || novoCurso.equals("")) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Aluno novoAluno = new Aluno();
        novoAluno.setNome(nome);
        novoAluno.setEmail(email);
        novoAluno.setTelefone(telefone);
        novoAluno.setCursoID(cursos.get(spnCursos.getSelectedItemPosition()).getId());
        if (dbAluno != null) {
            novoAluno.setId(dbAlunoID);
            db.alunoModel().update(novoAluno);
            Toast.makeText(this, "Aluno atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            db.alunoModel().insertAll(novoAluno);
            Toast.makeText(this, "Aluno cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void excluirAluno(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Exclusão de Aluno")
                .setMessage("Deseja excluir esse aluno?")
                .setPositiveButton("Sim", (dialog, which) -> excluir())
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