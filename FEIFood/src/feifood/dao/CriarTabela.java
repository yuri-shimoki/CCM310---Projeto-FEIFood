package feifood.dao;

import feifood.model.Alimento;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;
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
        
        String sql = "create table if not exists " + nome + "(";
        
        for (int i = 0; i < colunas.length - 1; ++i)
        {
            sql += colunas[i] + ",";
        }
        
        sql += colunas[colunas.length - 1] + ");";
        
        Statement statement = conexao.createStatement();
        statement.execute(sql);
                
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
                    "usuario_id integer not null references usuarios(id)"
        );
    }
    
    /**
     * Cria a tabela de alimentos no banco de dados. Preenche-a com alguns
     * alimentos caso esteja vazia para efeitos de demonstração.
     * 
     * @throws SQLException 
     */
    public static void criarTabelaAlimentos() throws SQLException
    {
        criarTabela("alimentos",
                    "id serial primary key",
                    "nome varchar(30) not null",
                    "valor numeric(10, 2) not null",
                    "tipo integer not null"
        );
        
        // Preenche a tabela com alguns elementos padrões se ela estiver vazia
        if (VerificarTabela.verificarSeTabelaEstaVazia("alimentos"))
        {
            AlimentoDao.inserir(new Alimento(null,
                                             "Batatas Fritas",
                                             new BigDecimal("5.99"),
                                             Alimento.Tipo.COMIDA.getCodigo()
            ));
            
            AlimentoDao.inserir(new Alimento(null,
                                             "Refrigerante Coca-cola 2L",
                                             new BigDecimal("15.99"),
                                             Alimento.Tipo.BEBIDA.getCodigo()
            ));
            
            AlimentoDao.inserir(new Alimento(null,
                                             "Cerveja Heineken",
                                             new BigDecimal("4.80"),
                                             Alimento.Tipo.BEBIDA_ALCOOLICA.getCodigo()
            ));
            
            AlimentoDao.inserir(new Alimento(null,
                                             "Suco de Laranja 1,5L",
                                             new BigDecimal("16.50"),
                                             Alimento.Tipo.BEBIDA.getCodigo()
            ));
            
            AlimentoDao.inserir(new Alimento(null,
                                             "Nuggets de Frango",
                                             new BigDecimal("7.80"),
                                             Alimento.Tipo.COMIDA.getCodigo()
            ));
            
            AlimentoDao.inserir(new Alimento(null,
                                             "Sorvete Kibon 500mL",
                                             new BigDecimal("11.99"),
                                             Alimento.Tipo.COMIDA.getCodigo()
            ));
            
            AlimentoDao.inserir(new Alimento(null,
                                             "Uísque Johnnie Walker 1L",
                                             new BigDecimal("54.99"),
                                             Alimento.Tipo.BEBIDA_ALCOOLICA.getCodigo()
            ));
            
            AlimentoDao.inserir(new Alimento(null,
                                             "Pizza de Calabresa",
                                             new BigDecimal("25.00"),
                                             Alimento.Tipo.COMIDA.getCodigo()
            ));
        }
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
        criarTabelaUsuarios();
        criarTabelaAlimentos();
        criarTabelaPedidos();
        criarTabelaPedidoAlimento();   
    }
}
