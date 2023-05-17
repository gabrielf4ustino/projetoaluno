package com.example.projetoaluno.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Curso {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nomeCurso;
    private int qtdeHoras;

    public Curso() {
    }

    public Curso(String nomeCurso, int qtdeHoras) {
        this.nomeCurso = nomeCurso;
        this.qtdeHoras = qtdeHoras;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public void setQtdeHoras(int qtdeHoras) {
        this.qtdeHoras = qtdeHoras;
    }

    public int getId() {
        return id;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public int getQtdeHoras() {
        return qtdeHoras;
    }

    @Override
    public String toString() {
        return
                "   Curso - " + nomeCurso + '\n' +
                "   Identificador - " + id  + '\n' +
                "   Quantidade de Horas - " + qtdeHoras +"hrs" + '\n' +
                '}';
    }
}
