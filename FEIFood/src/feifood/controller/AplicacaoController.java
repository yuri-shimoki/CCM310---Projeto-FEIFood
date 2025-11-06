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
                "Ocorreu um erro ao inicializar o programa:\n"
                    + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
        
        var loginController = new LoginController();
    }
}
