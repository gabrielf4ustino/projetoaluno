package com.example.projetoaluno.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.projetoaluno.dao.AlunoDao;
import com.example.projetoaluno.dao.CursoDao;
import com.example.projetoaluno.entities.Aluno;
import com.example.projetoaluno.entities.Curso;

@Database(entities = {Curso.class, Aluno.class}, version = 3)
public abstract class LocalDatabase extends RoomDatabase {
    private static LocalDatabase INSTANCE;

    public static LocalDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    LocalDatabase.class, "CursosOnline").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public abstract AlunoDao alunoModel();

    public abstract CursoDao cursoModel();
}