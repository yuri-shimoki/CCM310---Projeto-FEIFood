package feifood.controller;

import feifood.model.Alimento;
import feifood.view.AlimentoItemPanel;

/**
 * Classe que controla a criação de painéis de alimentos para no menu principal.
 */
public class AlimentoItemController
{   
    private AlimentoItemPanel painel;
    
    public AlimentoItemController(Alimento alimento)
    {
        
        
        painel.getDetalhesButton().addActionListener(e -> {
            
        });
        
        painel.getPlusButton().addActionListener(e -> {
            
        });
        
        painel.getMinusButton().addActionListener(e -> {
            
        });
    }
    
    public AlimentoItemPanel getAlimentoItemPanel()
    {   
        return painel;
    }
}
