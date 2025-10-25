package feifood.controller;

import feifood.view.CadastroFrame;
import feifood.controller.LoginController;

/**
 * Classe que controla a lógica de cadastro.
 */
public class CadastroController
{
    private CadastroFrame telaDeCadastro;
    
    public CadastroController()
    {
        telaDeCadastro = new CadastroFrame();
        
        telaDeCadastro.getVoltarButton().addActionListener(e -> {
                var loginController = new LoginController();
                telaDeCadastro.dispose();
        });
        
        telaDeCadastro.getCriarContaButton().addActionListener(e -> {
            
        });
        
        telaDeCadastro.setVisible(true);
    }
}
