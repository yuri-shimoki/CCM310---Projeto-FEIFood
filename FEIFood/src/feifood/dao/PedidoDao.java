package feifood.dao;

import feifood.model.Alimento;
import feifood.model.Pedido;
import feifood.model.Usuario;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Classe responsável por abstrair o acesso aos pedidos no banco de dados.
 */
public class PedidoDao
{
    
    /**
     * Realiza uma consulta SELECT aos pedidos no banco de dados.
     * 
     * @param pedido pedido a ser consultado.
     * @return o resultado da consulta ao banco de dados.
     * @throws SQLException 
     */
    public static ResultSet consultar(Pedido pedido) throws SQLException
    {
        Connection conexao = Conexao.getConexao();
        
        String sql = """
                     select
                        p.id as pedido_id
                        u.usuario_id as usuario_id
                        a.alimento_id as alimento_id
                        u.nome as nome_usuario
                        a.nome as nome_alimento
                        a.valor as valor_alimento
                        a.tipo as tipo_alimento
                        pa.quantidade_alimento as quantidade_alimento
                     from pedidos p
                     inner join usuarios u
                        on p.usuario_id = u.id
                     inner join pedido_alimento pa
                        on p.id = pa.pedido_id
                     inner join alimentos a
                        on pa.alimento_id = a.id
                     where p.id = ?;
                     """;
        
        PreparedStatement statement = conexao.prepareStatement(sql);
        
        statement.setInt(1, pedido.getId());
        
        statement.execute();
        
        ResultSet resultado = statement.getResultSet();
                
        conexao.close();
        
        Pedido resultadoPedido = null;
        
        var alimentos = new ArrayList<Alimento>();
        var quantidadesAlimentos = new ArrayList<Integer>();
        
        if (resultado.next())
        {
            resultadoPedido = new Pedido(resultado.getInt("pedido_id"),
                                         new Usuario(resultado.getInt("usuario_id"),
                                                     resultado.getString("nome_usuario"),
                                                     ""),
                                         null,
                                         null
            );
            
            do 
            {
                alimentos.add(new Alimento(resultado.getInt("alimento_id"),
                                       resultado.getString("nome_alimento"),
                                       resultado.getBigDecimal("valor_alimento"),
                                       resultado.getInt("tipo_alimento")
                ));
            
                quantidadesAlimentos.add(resultado.getInt("quantidade_alimento"));
            }
            while (resultado.next());
            
            resultadoPedido.setAlimentos(new ArrayList<Alimento>(alimentos));
            resultadoPedido.setQuantidades(new ArrayList<Integer>(quantidadesAlimentos));
        }
        
        return resultado;
    }
    
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
