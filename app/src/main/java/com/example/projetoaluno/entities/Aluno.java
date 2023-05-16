package com.example.projetoaluno.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
@Entity(foreignKeys = @ForeignKey(entity = Curso.class, parentColumns = "cursoID", childColumns = "cursoID", onDelete = ForeignKey.CASCADE))
public class Aluno {
    @PrimaryKey(autoGenerate = true)
    private int id;
    int cursoID;
    private String nome;
    private String curso;
    private String email;
    private String telefone;

    public Aluno() {
    }

    public Aluno(int cursoID, String nome, String curso, String email, String telefone) {
        this.cursoID = cursoID;
        this.nome = nome;
        this.curso = curso;
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

    public void setCurso(String curso) {
        this.curso = curso;
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

    public String getCurso() {
        return curso;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "alunoID=" + id +
                ", cursoID=" + cursoID +
                ", nomeAluno='" + nome + '\'' +
                ", curso='" + curso + '\'' +
                ", emailAluno='" + email + '\'' +
                ", telefoneAluno='" + telefone + '\'' +
                '}';
    }
}

