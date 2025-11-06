package feifood.dao;

import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Classe usada para verificar características das tabelas.
 */
public class VerificarTabela
{
    /**
     * Verifica se uma tabela está vazia
     * 
     * @param nomeTabela nome da tabela a ser checada
     * @return <code>true<code> se estiver vazia, senão <code>false</code>
     */
    public static boolean verificarSeTabelaEstaVazia(String nomeTabela)
    {
        try
        {
            Connection conexao = Conexao.getConexao();
            
            String sql = "select exists (select 1 from " + nomeTabela + ");";
            
            Statement statement = conexao.createStatement();
            ResultSet resultado = statement.executeQuery(sql);
            
            if (resultado.next())
            {
                return !resultado.getBoolean(1);
            }
            else
            {
                throw new SQLException("Não foi possível verificar se a "
                                        + "tabela existe.");
            }
        }
        catch (SQLException e)
        {
            return false;
        }
    }
}
