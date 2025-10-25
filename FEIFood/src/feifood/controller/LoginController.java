package feifood.controller;

import feifood.view.LoginFrame;

/**
 * Classe que controla a lógica de login.
 */
public class LoginController
{
    private LoginFrame telaDeLogin;
    
    public LoginController()
    {
        telaDeLogin = new LoginFrame();
        
        telaDeLogin.getCriarContaButton().addActionListener(e -> {
                var cadastroController = new CadastroController();
                telaDeLogin.dispose();
        });
        
        telaDeLogin.getEntrarButton().addActionListener(e -> {
            
        });
        
        telaDeLogin.setVisible(true);
    }
}
