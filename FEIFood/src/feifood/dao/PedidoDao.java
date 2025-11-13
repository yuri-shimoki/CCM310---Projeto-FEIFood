package feifood.dao;

import feifood.model.Alimento;
import feifood.model.Pedido;
import feifood.model.Usuario;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import javax.management.RuntimeErrorException;

/**
 * Classe responsável por abstrair o acesso aos pedidos no banco de dados.
 */
public class PedidoDao
{
    
    /**
     * Realiza uma consulta por id aos pedidos no banco de dados.
     * 
     * @param pedido pedido a ser consultado. Atributos fora <code>id</code>
     *      podem ser nulos.
     * @return o resultado da consulta ao banco de dados. <code>null<code> se
     *      nenhum resultado for retornado.
     * @throws SQLException 
     */
    public static Pedido consultarPorId(Pedido pedido) throws SQLException
    {
        if (pedido.getId() == null)
        {
            throw new RuntimeException("consultarPorId(pedido): id é nulo.");
        }
        
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
        Pedido resultadoPedido = new Pedido(null, null, null, null);
        
        var alimentos = new ArrayList<Alimento>();
        var quantidadesAlimentos = new ArrayList<Integer>();
        
        if (resultado.next())
        {
            resultadoPedido.setId(resultado.getInt("pedido_id"));
            
            resultadoPedido.setUsuario(new Usuario(resultado.getInt("usuario_id"),
                                                   resultado.getString("nome_usuario"),
                                                   null
            ));
            
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
        else
        {
            resultadoPedido = null;
        }
        
        conexao.close();
        
        return resultadoPedido;
    }
    
    /**
     * Salva o <code>pedido</code> no banco de dados. O atributo <code>id</code>
     * pode ser <code>null</code>.
     * 
     * @param pedido o pedido a ser inserido.
     * @throws SQLException 
     */
    public static void inserir(Pedido pedido) throws SQLException
    {
        Connection conexao = Conexao.getConexao();
        
        /* Bloco try-catch-finally utilizado para realizar uma transação.
         * Como 2 tabelas estão sendo modificadas, isto garante que, se um
         * erro ocorrer entre elas, não haverá incoerências no estado delas.
         * Em outras palavaras, isto garante uma política de "tudo ou nada".
         */
        try
        {
            conexao.setAutoCommit(false);
            
            String sql = """
                         insert into pedidos(usuario_id)
                         values (?)
                         returning id;
                         """;
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setInt(1, pedido.getUsuario().getId());
            ResultSet resultado = statement.executeQuery();

            if (resultado.next())
            {
                pedido.setId(resultado.getInt(1));
            }
            else
            {
                throw new SQLException("O id do pedido não foi retornado corretamente.");
            }

            sql = """
                  insert into pedido_alimento(pedido_id, alimento_id, quantidade_alimento)
                  values (?, ?, ?);
                  """;

            statement = conexao.prepareStatement(sql);

            for (int i = 0; i < pedido.getAlimentos().size(); ++i)
            {
                statement.setInt(1, pedido.getId());
                statement.setInt(2, pedido.getAlimentos().get(i).getId());
                statement.setInt(3, pedido.getQuantidades().get(i));
                statement.addBatch();
            }

            statement.executeBatch();

            conexao.commit();
        }
        catch (SQLException e)
        {
            conexao.rollback();
            throw e;
        }
        finally
        {
            conexao.close();
        }
    }
}
