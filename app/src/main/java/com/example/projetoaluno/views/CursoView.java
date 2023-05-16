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
    }

    public void salvarCurso(View view) {
        String nomeMarca = binding.edtCurso.getText().toString();
        if (nomeMarca.equals("")) {
            Toast.makeText(this, "Adicione uma marca.", Toast.LENGTH_SHORT).show();
            return;
        }

        Curso thisCurso = new Curso(nomeMarca, 0);

        if (dbCurso != null) {
            thisCurso.setId(dbCursoID);
            db.cursoModel().update(thisCurso);
            Toast.makeText(this, "Marca atualizada com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            db.cursoModel().insertAll(thisCurso);
            Toast.makeText(this, "Marca criada com sucesso.", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void excluirCurso(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Exclusão de Marca")
                .setMessage("Deseja excluir essa marca?")
                .setPositiveButton("Sim", (dialog, which) -> excluir())
                .setNegativeButton("Não", null)
                .show();
    }

    private void excluir() {
        try {
            db.cursoModel().delete(dbCurso);
            Toast.makeText(this, "Marca excluída com sucesso", Toast.LENGTH_SHORT).show();
        } catch (SQLiteConstraintException e) {
            Toast.makeText(this, "Impossível excluir marca com celulares cadastrados", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
    public void voltar(View view) {
        finish();
    }
}