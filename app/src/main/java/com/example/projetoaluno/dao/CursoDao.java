package com.example.projetoaluno.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projetoaluno.entities.Curso;

import java.util.List;
@Dao
public interface CursoDao {
    @Query("SELECT * FROM Curso WHERE cursoID = :id LIMIT 1")
    Curso getMarca(int id);
    @Query("SELECT * FROM Curso")
    List<Curso> getAll();
    @Insert
    void insertAll(Curso... curso);
    @Update
    void update(Curso cursos);
    @Delete
    void delete(Curso cursos);
}

