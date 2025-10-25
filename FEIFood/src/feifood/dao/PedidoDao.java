package feifood.dao;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

/**
 * Classe responsável por abstrair o acesso aos pedidos no banco de dados.
 */
public class PedidoDao
{
    private Connection connection;
    
    public PedidoDao()
    {
        
    }
}
