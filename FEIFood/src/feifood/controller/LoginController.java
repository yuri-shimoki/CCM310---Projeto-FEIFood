package feifood.controller;

import feifood.dao.UsuarioDao;
import feifood.model.Usuario;
import feifood.view.LoginFrame;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Classe que controla a lógica de login.
 */
public class LoginController
{
    private LoginFrame telaDeLogin;
    
    public LoginController()
    {
        telaDeLogin = new LoginFrame();
        
        telaDeLogin.setLocationRelativeTo(null);
        
        telaDeLogin.getCriarContaButton().addActionListener(e -> {
                var cadastroController = new CadastroController();
                telaDeLogin.dispose();
        });
        
        telaDeLogin.getEntrarButton().addActionListener(e -> {
            var nome = telaDeLogin.getNomeTextField().getText().trim();
            var senha = telaDeLogin.getSenhaTextField().getText().trim();
            
            if (nome.length() > 20)
            {
                JOptionPane.showMessageDialog(telaDeLogin,
                    "O nome não pode ter mais que 20 caracteres.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
                
                return;
            }
            
            if (senha.length() > 12)
            {
                JOptionPane.showMessageDialog(telaDeLogin,
                    "A senha não pode ter mais que 12 caracteres.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
                
                return;
            }
            
            if (nome.length() == 0 || senha.length() == 0)
            {
                JOptionPane.showMessageDialog(telaDeLogin,
                    "Nem o nome, nem a senha podem ser vazios.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
                
                return;
            }
            
            try
            {
                var resultado = UsuarioDao.consultarPorNomeESenha(
                    new Usuario(null, nome, senha)
                );
                
                if (resultado != null)
                {
                    var menuPrincipal = new MenuPrincipalController(resultado);
                    telaDeLogin.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(telaDeLogin,
                    "O usuário especificado não existe.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
                    
                    return;
                }
            }
            catch (SQLException e2)
            {
                JOptionPane.showMessageDialog(telaDeLogin,
                    "Ocorreu um erro ao consultar o banco de dados:\n"
                        + e2.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        
        telaDeLogin.setVisible(true);
    }
}
