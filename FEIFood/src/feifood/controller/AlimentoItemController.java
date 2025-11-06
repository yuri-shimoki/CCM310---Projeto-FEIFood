package feifood.controller;

import feifood.model.Alimento;
import feifood.model.AlimentoItemModel;
import feifood.view.AlimentoItemPanel;

/**
 * Classe que controla a criação de painéis de alimentos para no menu principal.
 */
public class AlimentoItemController
{   
    private AlimentoItemPanel painel;
    private AlimentoItemModel modelo;
    
    public AlimentoItemController(Alimento alimento)
    {
        painel = new AlimentoItemPanel();
        modelo = new AlimentoItemModel();
        
        painel.getDetalhesButton().addActionListener(e -> {
            var detalhesFrame = new AlimentoInfoController(alimento);
        });
        
        painel.getPlusButton().addActionListener(e -> {
            modelo.incrementarQuantidade();
            
            painel.getMinusButton().setEnabled(true);
        });
        
        painel.getMinusButton().addActionListener(e -> {
            if (modelo.getQuantidade() > 0)
            {
                if (modelo.getQuantidade() == 1)
                    painel.getMinusButton().setEnabled(false);
                
                modelo.decrementarQuantidade();
            }
        });
    }
    
    public AlimentoItemPanel getAlimentoItemPanel()
    {   
        return painel;
    }
}
