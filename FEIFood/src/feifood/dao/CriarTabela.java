package feifood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe responsável por criar tabelas no banco de dados.
 */
public class CriarTabela
{
    /**
     * Cria uma tabela genérica de nome <code>nome</code>, com colunas
     * <code>colunas...</code> escritas em SQL. É possível, também, especificar
     * restrições através do parâmetro <code>colunas</code>. Elas serão
     * executadas em ordem.
     * 
     * @param nome nome da tabela
     * @param colunas colunas da tabela escritas em SQL
     * @throws SQLException 
     */
    public static void criarTabela(String nome, String... colunas) throws SQLException
    {
        Connection conexao = Conexao.getConexao();
        
        String sql = "create table " + nome + "(";
        
        for (int i = 0; i < colunas.length - 1; ++i)
        {
            sql += colunas[i] + ",";
        }
        
        sql += colunas[colunas.length - 1] + ");";
        
        PreparedStatement statement = conexao.prepareStatement(sql);
        
        statement.execute();
                
        conexao.close();
    }
    
    /**
     * Cria a tabela de usuários no banco de dados.
     * 
     * @throws SQLException 
     */
    public static void criarTabelaUsuarios() throws SQLException
    {
        criarTabela("usuarios",
                    "id serial primary key",
                    "nome varchar(20) not null",
                    "senha varchar(12) not null"
        );
    }
    
    /**
     * Cria a tabela de pedidos no banco de dados.
     * 
     * @throws SQLException 
     */
    public static void criarTabelaPedidos() throws SQLException
    {
        criarTabela("pedidos",
                    "id serial primary key",
                    "usuario_id integer not null references usuarios(id)",
                    "senha varchar(12) not null"
        );
    }
    
    /**
     * Cria a tabela de alimentos no banco de dados.
     * 
     * @throws SQLException 
     */
    public static void criarTabelaAlimentos() throws SQLException
    {
        criarTabela("alimentos",
                    "id serial primary key",
                    "nome varchar(30) not null",
                    "valor numeric(10, 2) not null",
                    "tipo varchar(25) not null"
        );
    }
    
    /**
     * Cria a tabela que relaciona pedidos com alimentos no banco de dados.
     * 
     * @throws SQLException 
     */
    public static void criarTabelaPedidoAlimento() throws SQLException
    {
        criarTabela("pedido_alimento",
                    "pedido_id integer not null references pedidos(id)",
                    "alimento_id integer not null references alimentos(id)",
                    "quantidade_alimento integer not null "
                        + "check(quantidade_alimento > 0)",
                    "primary key(pedido_id, alimento_id)"
        );
    }
    
    /**
     * Cria as tabelas necessárias para o funcionamento do programa caso elas
     * ainda não existam no banco de dados.
     * 
     * @throws SQLException 
     */
    public static void criarTabelasSeNaoExistirem() throws SQLException
    {
        if (!VerificarBancoDeDados.verificarTabelaUsuariosExiste())
        {
            criarTabelaUsuarios();
        }

        if (!VerificarBancoDeDados.verificarTabelaAlimentosExiste())
        {
            criarTabelaAlimentos();
        }
        
        if (!VerificarBancoDeDados.verificarTabelaPedidosExiste())
        {
            criarTabelaPedidos();
        }
        
        if (!VerificarBancoDeDados.verificarTabelaPedidoAlimentoExiste())
        {
            criarTabelaPedidoAlimento();
        }
    }
}
