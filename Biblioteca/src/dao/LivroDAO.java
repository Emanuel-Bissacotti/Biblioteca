/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import bens.Autor;
import bens.Livro;
import conexao.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
    
    public List<Livro> getLivrosNomeAutor(String nomeAutor){
        String sql = "select * from Livro INNER JOIN autor ON autor.id = livro.id_autor where autor.nome LIKE ?;";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql, 
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, "%"+nomeAutor+"%");
            ResultSet rs = stmt.executeQuery();
            List<Livro> listaLivros = new ArrayList<>();
            while(rs.next()){
                Autor a = new Autor(rs.getInt("id_autor"), 
                                    rs.getString("nome"), 
                                    rs.getString("nacionalidade"), 
                                    rs.getString("data_nascimento")
                    );
                Livro l = new Livro(rs.getInt("id"),
                                    rs.getString("titulo"), 
                                    rs.getString("genero"),
                                    a,
                                    rs.getString("ano_publicacao"),
                                    rs.getInt("quantidade")
                    );
                listaLivros.add(l);
            }
            return listaLivros;
        }catch(SQLException ex){
            System.out.println("Erro ao consultar Livros: "+ ex.getMessage());
            return null;
        }
    }
    
}
