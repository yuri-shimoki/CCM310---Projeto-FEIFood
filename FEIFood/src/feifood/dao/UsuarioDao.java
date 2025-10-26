package feifood.dao;

import feifood.model.Usuario;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

/**
 * Classe responsável por abstrair o acesso aos usuários no banco de dados.
 */
public class UsuarioDao
{   
    private UsuarioDao() {}
    
    /**
     * Realiza uma consulta SELECT aos usuários no banco de dados.
     * 
     * @param usuario o usuario a ser consultado.
     * @return o resultado da consulta ao banco de dados.
     * @throws SQLException 
     */
    public static ResultSet consultar(Usuario usuario) throws SQLException
    {
        Connection conexao = Conexao.getConexao();
        
        String sql = "select * from usuarios where nome = ? and senha = ?;";
        PreparedStatement statement = conexao.prepareStatement(sql);
        
        statement.setString(1, usuario.getNome());
        statement.setString(2, usuario.getSenha());
        
        statement.execute();
        
        ResultSet resultado = statement.getResultSet();
                
        conexao.close();
        
        return resultado;
    }
    
    /**
     * Salva o <code>usuario</code> no banco de dados.
     * 
     * @param usuario o usuario a ser inserido.
     * @throws SQLException 
     */
    public static void inserir(Usuario usuario) throws SQLException
    {
        Connection conexao = Conexao.getConexao();
        
        String sql = "insert into usuarios(nome, senha) values (?, ?);";
        PreparedStatement statement = conexao.prepareStatement(sql);
        
        statement.setString(1, usuario.getNome());
        statement.setString(2, usuario.getSenha());
        
        statement.execute();
                
        conexao.close();
    }
}
