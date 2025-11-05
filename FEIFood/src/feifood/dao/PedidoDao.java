package feifood.dao;

import feifood.model.Pedido;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

/**
 * Classe responsável por abstrair o acesso aos pedidos no banco de dados.
 */
public class PedidoDao
{
//    
//    /**
//     * Realiza uma consulta SELECT aos usuários no banco de dados.
//     * 
//     * @param alimento o alimento a ser consultado.
//     * @return o resultado da consulta ao banco de dados.
//     * @throws SQLException 
//     */
//    public static ResultSet consultar(Pedido pedido) throws SQLException
//    {
//        Connection conexao = Conexao.getConexao();
//        
//        String sql = "select * from pedidos where id = ?;";
//        PreparedStatement statement = conexao.prepareStatement(sql);
//        
//        statement.setInt(1, pedido.getId());
//        
//        statement.execute();
//        
//        ResultSet resultado = statement.getResultSet();
//                
//        conexao.close();
//        
//        return resultado;
//    }
//    
//    /**
//     * Salva o <code>alimento</code> no banco de dados.
//     * 
//     * @param alimento o alimento a ser inserido.
//     * @throws SQLException 
//     */
//    public static void inserir(Pedido pedido) throws SQLException
//    {
//        Connection conexao = Conexao.getConexao();
//        
//        String sql = "insert into usuarios(id, nome, senha) values (?, ?, ?);";
//        PreparedStatement statement = conexao.prepareStatement(sql);
//        
//        statement.setInt(1, alimento.getId());
//        statement.setString(2, alimento.getNome());
//        statement.setString(3, alimento.getSenha());
//        
//        statement.execute();
//                
//        conexao.close();
//    }
}
