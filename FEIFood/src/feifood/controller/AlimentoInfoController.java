package feifood.controller;

import feifood.view.AlimentoInfoFrame;
import feifood.dao.AlimentoDao;
import feifood.model.Alimento;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 */
public class AlimentoInfoController
{
    private AlimentoInfoFrame telaDeAlimento;
    
    public AlimentoInfoController(String nome)
    {
        telaDeAlimento = new AlimentoInfoFrame();
        telaDeAlimento.setLocationRelativeTo(null);
        
        try
        {
            var resultado = AlimentoDao.consultarPorNome(
                new Alimento(null, nome, null, -1)
            );
            
            telaDeAlimento.getNomeText().setText(resultado.getNome());
            telaDeAlimento.getValorText().setText(resultado.getValor().toString());
            telaDeAlimento.getTipoText().setText(resultado.getTipo().toString());
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(telaDeAlimento,
                    "Ocorreu um erro ao consultar os dados do alimento no "
                        + "banco de dados:\n" + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        telaDeAlimento.getVoltarButton().addActionListener(e -> {
            var menuPrincipal = new MenuPrincipalController();
            telaDeAlimento.dispose();
        });
        
        telaDeAlimento.setVisible(true);
    }
    
}
