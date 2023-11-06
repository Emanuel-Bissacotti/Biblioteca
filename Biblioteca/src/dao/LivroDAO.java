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
    
    public void excluirLivroIDAutor(int idAutor){
        try{
            String sql = "DELETE FROM livro WHERE id_autor = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idAutor);
            stmt.execute();
        }catch(SQLException ex){
            System.out.println("Erro ao deletar Livros do autor: "+ex.getMessage());
        }
    }
    
    public Livro getLivro(int id){
        String sql = "SELECT * FROM livro WHERE id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql, 
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.first();
            
            AutorDAO aDAO = new AutorDAO();
            Autor a = aDAO.getAutor(rs.getInt("id_autor"));
            
            Livro l = new Livro(rs.getInt("id"), 
                                rs.getString("titulo"), 
                                rs.getString("genero"), 
                                a, 
                                rs.getString("ano_publicacao"), 
                                rs.getInt("quantidade")
                );
            
            return l;
        }catch(SQLException ex){
            System.out.println("Erro ao consultar livro: "+ ex.getMessage());
            return null;
        }
    }
    
    public void update(Livro livro){
        try{
            String sql = "UPDATE livro set titulo = ?, genero = ?, ano_publicacao = ?, quantidade = ?, id_autor = ? WHERE id=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,livro.getTitulo());
            stmt.setString(2, livro.getGenero());
            stmt.setString(3, livro.getAno_publicacao());
            stmt.setInt(4, livro.getQuantidade());
            stmt.setInt(5, livro.getAutor().getId());
            stmt.setInt(6, livro.getId());
            stmt.execute();
        }catch(SQLException ex){
            System.out.println("Erro ao atualiza Autor: "+ex.getMessage());
        }
    }
    
    public void excluirLivro(int livroID){
        try{
                String sql = "DELETE FROM livro WHERE id=?";

                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, livroID);
                stmt.execute();
        }catch(SQLException ex){
            System.out.println("Erro ao deletar livro: "+ex.getMessage());
        }
    }
}
