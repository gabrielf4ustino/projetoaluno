package com.example.projetoaluno.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projetoaluno.entities.Aluno;

@Dao
public interface AlunoDao {
    @Query("SELECT * FROM Aluno WHERE id = :id LIMIT 1")
    Aluno getCel(int id);
    @Update
    void update(Aluno aluno);
    @Insert
    void insertAll(Aluno... aluno);
    @Delete
    void delete(Aluno aluno);
    /*
    @Query("SELECT * FROM Celular")
    List<Celular> getAll();
    @Query("SELECT * FROM Celular WHERE celularID IN (:celularId)")
    List<Celular> loadAllByIds(int[] celularId);
    @Query("SELECT * FROM Celular WHERE modelo LIKE :name LIMIT 1")
    Celular findByName(String name);
    @Query("UPDATE Celular SET modelo =:model WHERE celularID == :celularId")
    void updateName(String model, int celularId);
     */
}