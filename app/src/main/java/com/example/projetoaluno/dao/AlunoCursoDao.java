package com.example.projetoaluno.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.projetoaluno.entities.AlunoCurso;

import java.util.List;
@Dao
public interface AlunoCursoDao {
    @Query("SELECT Aluno.id AS alunoID, Aluno.curso " +
            "AS alunoModelo, Curso.nomeCurso AS cursoName " +
            "FROM Aluno INNER JOIN Curso ON Aluno.cursoID = Curso.cursoID")
    List<AlunoCurso> getAllCelMarca();

   /* @Query("SELECT * FROM Celular WHERE celularID = :id LIMIT 1")
    CelularMarca getCelId(int id);*/
}
