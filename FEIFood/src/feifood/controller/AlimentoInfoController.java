package feifood.controller;

import feifood.view.AlimentoInfoFrame;
import feifood.dao.AlimentoDao;
import feifood.model.Alimento;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
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
        
        NumberFormat formatoBr = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        telaDeAlimento.getValorText().setText(formatoBr.format(alimento.getValor()));
        telaDeAlimento.getTipoText().setText(alimento.getTipo().toString());
        
        telaDeAlimento.getVoltarButton().addActionListener(e -> {
            telaDeAlimento.dispose();
        });
        
        telaDeAlimento.setVisible(true);
    }
    
}
