/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bens;

/**
 *
 * @author laboratorio
 */
public class Autor {
    private int id;
    private String nome;
    private String nacionalidade;
    private String data_nascimento;

    public Autor(String nome, String nacionalidade, String data_nascimento) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.data_nascimento = data_nascimento;
    }

    public Autor(int id, String nome, String nacionalidade, String data_nascimento) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.data_nascimento = data_nascimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.nome;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return id == autor.id;
    }
}
