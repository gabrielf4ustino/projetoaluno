package com.example.projetoaluno.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projetoaluno.entities.Aluno;

import java.util.List;

@Dao
public interface AlunoDao {
    @Query("SELECT * FROM Aluno WHERE id = :id LIMIT 1")
    Aluno getAlunoById(int id);
    @Query("SELECT * FROM Aluno")
    List<Aluno> getAll();
    @Update
    void update(Aluno aluno);
    @Insert
    void insertAll(Aluno... aluno);
    @Delete
    void delete(Aluno aluno);
}