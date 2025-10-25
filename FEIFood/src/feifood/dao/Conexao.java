package feifood.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe que abstrai a conexão ao banco de dados.
 */
public class Conexao
{   
    private Conexao() {}
    
    /**
     * Método estático que cria uma nova conexão ao banco de dados.
     * 
     * @return uma conexão ao banco de dados.
     * @throws SQLException 
     */
    public static Connection getConexao() throws SQLException
    {
        Connection conexao = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/feifood",
                "postgres",
                "feifood");
        
        return conexao;
    }
}
