/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import bens.Livro;
import conexao.Conexao;
import java.sql.*;
/**
 *
 * @author laboratorio
 */
public class LivroDAO {
    private Conexao conexao;
    private Connection conn;
    
    public LivroDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void saveLivro(Livro livro){
        String sql = "INSERT INTO livro (titulo, genero, ano_publicacao, quantidade, id_autor) VALUES (?, ?, ?, ?, ?);";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getGenero());
            stmt.setString(3, livro.getAno_publicacao());
            stmt.setInt(4, livro.getQuantidade());
            stmt.setInt(5, livro.getAutor().getId());
            stmt.execute();
        }
        catch(SQLException e){
            System.out.println("Erro ao inserir Livro: "+ e.getMessage());
        }
    }
}
