package com.example.projetoaluno.views;

import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetoaluno.database.LocalDatabase;
import com.example.projetoaluno.databinding.ActivityCursoViewBinding;
import com.example.projetoaluno.entities.Curso;

public class CursoView extends AppCompatActivity {
    private LocalDatabase db;
    private ActivityCursoViewBinding binding;
    private int dbCursoID;
    private Curso dbCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCursoViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        dbCursoID = getIntent().getIntExtra("CURSO_SELECIONADA_ID", -1);
    }
    protected void onResume() {
        super.onResume();
        if(dbCursoID >= 0) {
            getDBMarca();
        } else {
            binding.btnExcluirMarca.setVisibility(View.GONE);
        }
    }
    private void getDBMarca() {
        dbCurso = db.cursoModel().getCursoById(dbCursoID);
        binding.edtCurso.setText(dbCurso.getNomeCurso());
        binding.edtHour.setText(String.valueOf(dbCurso.getQtdeHoras()));
    }

    public void salvarCurso(View view) {
        String nomeMarca = binding.edtCurso.getText().toString();
        String qtdHoras = binding.edtHour.getText().toString();
        int number;
        if (nomeMarca.equals("")) {
            Toast.makeText(this, "Adicione um curso.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            number = Integer.parseInt(qtdHoras);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Coloque apenas numeros na Carga Horaria", Toast.LENGTH_SHORT).show();
            return;
        }
        Curso thisCurso = new Curso(nomeMarca, number);

        if (dbCurso != null) {
            thisCurso.setId(dbCursoID);
            db.cursoModel().update(thisCurso);
            Toast.makeText(this, "Curso atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            db.cursoModel().insertAll(thisCurso);
            Toast.makeText(this, "Curso criado com sucesso.", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void excluirCurso(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Exclusão de Curso")
                .setMessage("Deseja excluir esse curso?")
                .setPositiveButton("Sim", (dialog, which) -> excluir())
                .setNegativeButton("Não", null)
                .show();
    }

    private void excluir() {
        try {
            db.cursoModel().delete(dbCurso);
            Toast.makeText(this, "Curso excluído com sucesso", Toast.LENGTH_SHORT).show();
        } catch (SQLiteConstraintException e) {
            Toast.makeText(this, "Impossível excluir curso com alunos cadastrados", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
    public void voltar(View view) {
        finish();
    }
}
