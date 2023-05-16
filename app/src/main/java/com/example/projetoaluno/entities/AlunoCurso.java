package com.example.projetoaluno.entities;

public class AlunoCurso {
    public int alunoID;
    public String aluno;
    public String curso;

    public AlunoCurso() {
    }

    public AlunoCurso(String aluno, String curso) {
        this.aluno = aluno;
        this.curso = curso;
    }

    public void setAlunoID(int alunoID) {
        this.alunoID = alunoID;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getAlunoID() {
        return alunoID;
    }

    public String getAluno() {
        return aluno;
    }

    public String getCurso() {
        return curso;
    }

    @Override
    public String toString() {
        return "AlunoCurso{" +
                "alunoID=" + alunoID +
                ", alunoCurso='" + aluno + '\'' +
                ", cursoNome='" + curso + '\'' +
                '}';
    }
}
