package feifood.controller;

import feifood.dao.CriarTabela;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * A classe principal que é responsável por conectar todos os outros módulos
 * desta aplicação.
 */
public class AplicacaoController
{
    public AplicacaoController()
    {
        try
        {
            CriarTabela.criarTabelasSeNaoExistirem();
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null,
                "Um erro ocorreu ao verificar a existência do " +
                    "banco de dados:\n" + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
        
        var loginController = new LoginController();
    }
}
