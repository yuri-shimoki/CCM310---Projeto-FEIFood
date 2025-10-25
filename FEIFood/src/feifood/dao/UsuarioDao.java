package feifood.dao;

import feifood.dao.Conexao;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

/**
 * Classe responsável por abstrair o acesso aos usuários no banco de dados.
 */
public class UsuarioDao
{
    private Conexao conexao;
    
    public UsuarioDao(Conexao conexao)
    {
        this.conexao = conexao;
    }
}
