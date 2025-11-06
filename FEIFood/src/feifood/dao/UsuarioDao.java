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
     * Realiza uma consulta SELECT nos usuários no banco de dados com base no
     * <code>nome</code> e <code>senha</code> do usuário.
     * 
     * @param usuario o usuário a ser consultado. O atributo <code>id</code>
     *      pode ser nulo.
     * @return o resultado da consulta ao banco de dados. <code>null</code> se
     *      a consultar não tiver resultados.
     * @throws SQLException 
     */
    public static Usuario consultarPorNomeESenha(Usuario usuario)
        throws SQLException
    {
        if (usuario.getNome() == null || usuario.getSenha() == null)
        {
            throw new RuntimeException("consultarPorNomeESenha(usuario): nome ou"
                + "senha são nulos.");
        }
        
        Connection conexao = Conexao.getConexao();
        
        String sql = """
                     select
                        id,
                        nome,
                        senha
                     from usuarios
                     where nome = ? and senha = ?;
                     """;
        PreparedStatement statement = conexao.prepareStatement(sql);
        
        statement.setString(1, usuario.getNome());
        statement.setString(2, usuario.getSenha());
        
        ResultSet resultado = statement.executeQuery();
        
        Usuario usuarioRequisitado = new Usuario(null, null, null);
        
        if (resultado.next())
        {
            usuarioRequisitado.setId(resultado.getInt("id"));
            usuarioRequisitado.setNome(resultado.getString("nome"));
            usuarioRequisitado.setSenha(resultado.getString("senha"));
        }
        else
        {
            usuarioRequisitado = null;
        }
        
        conexao.close();
        
        return usuarioRequisitado;
    }
    
    /**
     * Salva o <code>usuario</code> no banco de dados.
     * 
     * @param usuario o usuario a ser inserido.
     * @throws SQLException 
     */
    public static void inserir(Usuario usuario) throws SQLException
    {
        if (usuario.getNome() == null || usuario.getSenha() == null)
        {
            throw new RuntimeException("inserir(): nome ou"
                + "senha são nulos.");
        }
        
        Connection conexao = Conexao.getConexao();
        
        String sql = """
                     insert into usuarios(nome, senha)
                     values (?, ?)
                     returning id;
                     """;
        
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setString(1, usuario.getNome());
        statement.setString(2, usuario.getSenha());
        
        ResultSet resultado = statement.executeQuery();
        
        if (resultado.next())
        {
            usuario.setId(resultado.getInt(1));
        }
        else
        {
            conexao.close();
            throw new SQLException("O id não foi gerado corretamente ao "
                + "inserir o usuário no banco de dados.");
        }
                
        conexao.close();
    }
}
