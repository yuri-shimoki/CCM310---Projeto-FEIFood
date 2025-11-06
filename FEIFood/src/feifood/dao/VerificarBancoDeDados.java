package feifood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Classe que permite verificar a existência das tabelas no banco de dados.
 */
public class VerificarBancoDeDados
{
    /**
     * Verifica se a tabela <code>nome</code> existe.
     * 
     * @param nome nome da tabela
     * @return booleana dizendo se a tabela especificada existe.
     * @throws SQLException 
     */
    public static boolean verificarTabelaExiste(String nome) throws SQLException
    {
        Connection conexao = Conexao.getConexao();
        
        PreparedStatement statement = conexao.prepareStatement(
            "select exists (select 1 from pg_catalog.pg_tables where "
                + "schemaname = 'public' and tablename = ?);"
        );
    
        statement.setString(1, nome);

        ResultSet resultado = statement.executeQuery();
        
        boolean existe = false;
        
        if (resultado.next())
        {
            existe = resultado.getBoolean(1);
        }
        
        conexao.close();
        
        return existe;
    }
    
    /**
     * Verifica se a tabela <code>usuarios</code> existe.
     * 
     * @return booleana dizendo se a tabela existe.
     * @throws SQLException 
     */
    public static boolean verificarTabelaUsuariosExiste() throws SQLException
    {
        return verificarTabelaExiste("usuarios");
    }
    
    /**
     * Verifica se a tabela <code>pedidos</code> existe.
     * 
     * @return booleana dizendo se a tabela existe.
     * @throws SQLException 
     */
    public static boolean verificarTabelaPedidosExiste() throws SQLException
    {
        return verificarTabelaExiste("pedidos");
    }
    
    /**
     * Verifica se a tabela <code>alimentos</code> existe.
     * 
     * @return booleana dizendo se a tabela existe.
     * @throws SQLException 
     */
    public static boolean verificarTabelaAlimentosExiste() throws SQLException
    {
        return verificarTabelaExiste("alimentos");
    }
    
    /**
     * Verifica se a tabela <code>pedido_alimento</code> existe.
     * 
     * @return booleana dizendo se a tabela existe.
     * @throws SQLException 
     */
    public static boolean verificarTabelaPedidoAlimentoExiste() throws SQLException
    {
        return verificarTabelaExiste("alimentos");
    }
}
