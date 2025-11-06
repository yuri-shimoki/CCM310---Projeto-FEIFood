package feifood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import feifood.model.Alimento;
import java.util.ArrayList;

/**
 * Classe responsável por abstrair o acesso aos alimentos no banco de dados.
 */
public class AlimentoDao
{
    
    /**
     * Realiza uma consulta case-insensitive por nome nos alimentos no
     * banco de dados.
     * 
     * @param alimento alimento a ser consultado. Seus atributos, exceto por
     *      <code>nome</code>, podem ser nulos.
     * @return o resultado da consulta ao banco de dados. <code>null<code> se
     *      nenhum resultado for obtido.
     * @throws SQLException 
     */
    public static Alimento consultarPorNome(Alimento alimento) throws SQLException
    {
        if (alimento.getNome() == null)
        {
            throw new RuntimeException("consultarPorNome(alimento): nome é nulo.");
        }
        
        Connection conexao = Conexao.getConexao();
        
        String sql = """
                     select
                        id,
                        nome,
                        valor,
                        tipo
                     from alimentos
                     where nome = ?;
                     """;
        PreparedStatement statement = conexao.prepareStatement(sql);
        
        statement.setString(1, alimento.getNome());
        
        ResultSet resultado = statement.executeQuery();
        
        Alimento alimentoRequisitado = null;
        
        if (resultado.next())
        {
            String nome = resultado.getString("nome").toLowerCase();
            if (alimento.getNome().toLowerCase().equals(nome))
            {
                alimentoRequisitado = new Alimento(resultado.getInt("id"),
                                                   alimento.getNome(),
                                                   resultado.getBigDecimal("valor"),
                                                   resultado.getInt("tipo")
                );
            }
        }
        
        conexao.close();
        
        return alimentoRequisitado;
    }
    
    /**
     * Retorna todos os alimentos registrados no banco de dados.
     * 
     * @return um array contendo todos os alimentos no banco de dados.
     *      <code>null</code> se a consultar não tiver resultados.
     * @throws SQLException 
     */
    public static ArrayList<Alimento> obterTodosOsAlimentos() throws SQLException
    {
        Connection conexao = Conexao.getConexao();
        
        String sql = """
                     select
                        id,
                        nome,
                        valor,
                        tipo
                     from alimentos;
                     """;
        
        PreparedStatement statement = conexao.prepareStatement(sql);
        
        ResultSet resultado = statement.executeQuery();
        
        resultado.next();
        
        ArrayList<Alimento> alimentos = null;
        
        while (resultado.next())
        {
            alimentos.add(new Alimento(resultado.getInt("id"),
                                       resultado.getString("nome"),
                                       resultado.getBigDecimal("valor"),
                                       resultado.getInt("tipo")
            ));
        }
        
        conexao.close();
        
        return alimentos;
    }
    
    /**
     * Salva o <code>alimento</code> no banco de dados.
     * 
     * @param alimento o alimento a ser inserido. O atributo
     *      <code>id</code> pode ser <code>null</code>.
     * @throws SQLException 
     */
    public static void inserir(Alimento alimento) throws SQLException
    {
        if (alimento.getNome() == null || alimento.getValor() == null)
        {
            throw new RuntimeException("inserir(alimento): nome ou valor são "
                + "nulos.");
        }
        
        Connection conexao = Conexao.getConexao();
        
        String sql = """
                     insert into alimentos(nome, valor, tipo)
                     values (?, ?, ?)
                     returning id;
                     """;
        
        PreparedStatement statement = conexao.prepareStatement(sql);
        
        statement.setString(1, alimento.getNome());
        statement.setBigDecimal(2, alimento.getValor());
        statement.setInt(3, alimento.getTipo().getCodigo());
        
        ResultSet resultado = statement.executeQuery();
        
        if (resultado.next())
        {
            alimento.setId(resultado.getInt(1));
        }
        else
        {
            conexao.close();
            throw new SQLException("O id não foi gerado corretamente ao "
                + "inserir alimento no banco de dados.");
        }
                
        conexao.close();
    }
}
