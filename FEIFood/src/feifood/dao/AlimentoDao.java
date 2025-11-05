package feifood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import feifood.model.Alimento;

/**
 * Classe responsável por abstrair o acesso aos alimentos no banco de dados.
 */
public class AlimentoDao
{
    /**
     * Realiza uma consulta SELECT nos alimentos no banco de dados.
     * 
     * @param alimento o alimento a ser consultado.
     * @return o resultado da consulta ao banco de dados.
     * @throws SQLException 
     */
    public static Alimento consultar(Alimento alimento) throws SQLException
    {
        Connection conexao = Conexao.getConexao();
        
        String sql = "select * from alimentos where id = ?;";
        PreparedStatement statement = conexao.prepareStatement(sql);
        
        statement.setInt(1, alimento.getId());
        
        statement.execute();
        
        ResultSet resultado = statement.getResultSet();
                
        conexao.close();
        
        resultado.next();
        
        Alimento alimentoRequisitado = null;
        
        if (resultado.next())
        {
            alimentoRequisitado = new Alimento(resultado.getInt("id"),
                                               resultado.getString("nome"),
                                               resultado.getBigDecimal("valor"),
                                               resultado.getInt("tipo")
            );
        }
        
        return alimentoRequisitado;
    }
    
    /**
     * Salva o <code>alimento</code> no banco de dados.
     * 
     * @param alimento o alimento a ser inserido.
     * @throws SQLException 
     */
    public static void inserir(Alimento alimento) throws SQLException
    {
        Connection conexao = Conexao.getConexao();
        
        String sql = "insert into usuarios(id, nome, valor, tipo) "
            + "values (?, ?, ?, ?);";
        
        PreparedStatement statement = conexao.prepareStatement(sql);
        
        statement.setInt(1, alimento.getId());
        statement.setString(2, alimento.getNome());
        statement.setBigDecimal(3, alimento.getValor());
        statement.setInt(3, alimento.getTipo().getCodigo());
        
        statement.execute();
                
        conexao.close();
    }
}
