package feifood.controller;

import feifood.view.MenuPrincipalFrame;
import feifood.dao.AlimentoDao;

/**
 *
 */
public class MenuPrincipalController {
    
    private MenuPrincipalFrame telaPrincipal;
    
    public MenuPrincipalController()
    {
        telaPrincipal = new MenuPrincipalFrame();
        telaPrincipal.setLocationRelativeTo(null);
        
        /* Acessar AlimentoDao e inserir os alimentos ao JScrollPane de
         * telaPrincipal.
         */        
        
        
        
        // Criar tela de histórico de pedidos?
        
        telaPrincipal.getBuscarButton().addActionListener(e -> {
        
        });
        
        telaPrincipal.getCriarPedidoButton().addActionListener(e -> {
        
        });
        
        telaPrincipal.setVisible(true);
    }
}
