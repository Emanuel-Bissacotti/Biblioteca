package dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import bens.Autor;
import java.sql.*;
import conexao.Conexao;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author laboratorio
 */
public class AutorDAO {
    private Conexao conexao;
    private Connection conn;
    
    public AutorDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void saveAutor(Autor autor){
        String sql = "INSERT INTO autor (nome, nacionalidade, data_nascimento) VALUES (?, ?, ?);";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getNacionalidade());
            stmt.setString(3, autor.getData_nascimento());
            
            stmt.execute();
        }
        catch(SQLException e){
            System.out.println("Erro ao inserir autor: "+ e.getMessage());
        }
    }
    
    public Autor getAutor(int id){
        String sql = "SELECT * FROM autor WHERE id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql, 
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.first();
            
            Autor a = new Autor(id, 
                                rs.getString("nome"), 
                                rs.getString("nacionalidade"), 
                                rs.getString("data_nascimento")
                );
            
            return a;
        }catch(SQLException ex){
            System.out.println("Erro ao consultar autor: "+ ex.getMessage());
            return null;
        }
    }
    
    public List<Autor> getAutores(String nome){
        String sql = "SELECT * FROM autor WHERE nome LIKE ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql, 
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, "%"+nome+"%");
            ResultSet result = stmt.executeQuery();
            List<Autor> listaAutor = new ArrayList<>();
            
            while(result.next()){
                Autor autor = new Autor(result.getInt("id"), 
                                    result.getString("nome"), 
                                    result.getString("nacionalidade"), 
                                    result.getString("data_nascimento")
                );
                listaAutor.add(autor);
            }
            return listaAutor;
        } catch (SQLException e) {
            System.out.println("Erro ao pegar todos os alunos: "+ e.getMessage());
        }
        return null;
    }
    
    public void update(Autor autor){
        try{
            String sql = "UPDATE autor set nome=?, nacionalidade=?, data_nascimento=? WHERE id=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,autor.getNome());
            stmt.setString(2, autor.getNacionalidade());
            stmt.setString(3, autor.getData_nascimento());
            stmt.setInt(4, autor.getId());
            stmt.execute();
        }catch(SQLException ex){
            System.out.println("Erro ao atualiza Autor: "+ex.getMessage());
        }
    }
}
