package com.example.projetoaluno.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
@Entity(foreignKeys = @ForeignKey(entity = Curso.class, parentColumns = "id", childColumns = "cursoID", onDelete = ForeignKey.CASCADE))
public class Aluno {
    @PrimaryKey(autoGenerate = true)
    private int id;
    int cursoID;
    private String nome;
    private String email;
    private String telefone;

    public Aluno() {
    }

    public Aluno(int cursoID, String nome, String email, String telefone) {
        this.cursoID = cursoID;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCursoID(int cursoID) {
        this.cursoID = cursoID;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public int getCursoID() {
        return cursoID;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override
    public String toString() {
        return
                "   nome - " + nome + "   [" + id + "]"+ '\n' +
                "   Curso - " + cursoID + '\n' +
                "   email - " + email + '\n' +
                "   telefone - " + telefone + '\n' +
                '}';
    }
}

