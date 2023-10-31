/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bens;

/**
 *
 * @author laboratorio
 */
public class Livro {
    int id;
    String titulo;
    String genero;
    Autor autor;
    String ano_publicacao;
    int quantidade;

    public Livro(String titulo, String genero, Autor autor, String ano_publicacao, int quantidade) {
        this.titulo = titulo;
        this.genero = genero;
        this.autor = autor;
        this.ano_publicacao = ano_publicacao;
        this.quantidade = quantidade;
    }

    public Livro(int id, String titulo, String genero, Autor autor, String ano_publicacao, int quantidade) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.autor = autor;
        this.ano_publicacao = ano_publicacao;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAno_publicacao() {
        return ano_publicacao;
    }

    public void setAno_publicacao(String ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    
    
    
}
