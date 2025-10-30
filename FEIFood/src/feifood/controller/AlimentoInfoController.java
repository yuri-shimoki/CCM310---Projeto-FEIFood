package feifood.controller;

import feifood.view.AlimentoInfoFrame;
import feifood.dao.AlimentoDao;

/**
 *
 */
public class AlimentoInfoController
{
    private AlimentoInfoFrame telaDeAlimento;
    
    public AlimentoInfoController(String nomeAlimento)
    {
        telaDeAlimento = new AlimentoInfoFrame();
        telaDeAlimento.setLocationRelativeTo(null);
        
        // Criar uma tela com os dados do alimento com base em nomeAlimento
        // Acessar AlimentoDao
        
        telaDeAlimento.getVoltarButton().addActionListener(e -> {
            var menuPrincipal = new MenuPrincipalController();
            telaDeAlimento.dispose();
        });
        
        telaDeAlimento.setVisible(true);
    }
    
}
