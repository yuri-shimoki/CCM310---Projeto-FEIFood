package feifood.controller;

import feifood.view.AlimentoInfoFrame;
import feifood.dao.AlimentoDao;
import feifood.model.Alimento;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 */
public class AlimentoInfoController
{
    private AlimentoInfoFrame telaDeAlimento;
    
    public AlimentoInfoController(Alimento alimento)
    {
        telaDeAlimento = new AlimentoInfoFrame();
        telaDeAlimento.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        telaDeAlimento.setLocationRelativeTo(null);
        
        telaDeAlimento.getNomeText().setText(alimento.getNome());
        telaDeAlimento.getValorText().setText(alimento.getValor().toString());
        telaDeAlimento.getTipoText().setText(alimento.getTipo().toString());
        
        telaDeAlimento.getVoltarButton().addActionListener(e -> {
            var menuPrincipal = new MenuPrincipalController();
            telaDeAlimento.dispose();
        });
        
        telaDeAlimento.setVisible(true);
    }
    
}
