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
    /**
     * Realiza uma consulta SELECT nos usuários no banco de dados via
     * nome e senha.
     * 
     * @param nome o nome do usuário a ser consultado.
     * @param senha a senha do usuário a ser consultado.
     * @return o resultado da consulta ao banco de dados.
     * @throws SQLException 
     */
    public static Usuario consultarPorNomeESenha(String nome, String senha)
        throws SQLException
    {
        Connection conexao = Conexao.getConexao();
        
        String sql = "select * from usuarios where nome = ? and senha = ?;";
        PreparedStatement statement = conexao.prepareStatement(sql);
        
        statement.setString(1, nome);
        statement.setString(2, senha);
        
        statement.execute();
        
        ResultSet resultado = statement.getResultSet();
                
        conexao.close();
        
        Usuario usuarioRequisitado = null;
        
        if (resultado.next())
        {
            usuarioRequisitado = new Usuario(resultado.getInt("id"),
                                             resultado.getString("nome"),
                                             resultado.getString("senha")
            );
        }
        
        return usuarioRequisitado;
    }
    
    /**
     * Salva o <code>usuario</code> no banco de dados.
     * 
     * @param usuario o usuario a ser inserido.
     * @throws SQLException 
     */
    public static void inserir(String nome, String senha) throws SQLException
    {
        Connection conexao = Conexao.getConexao();
        
        String sql = "insert into usuarios(nome, senha) values (?, ?);";
        PreparedStatement statement = conexao.prepareStatement(sql);
        
        statement.setString(1, nome);
        statement.setString(2, senha);
        
        statement.execute();
                
        conexao.close();
    }
}
