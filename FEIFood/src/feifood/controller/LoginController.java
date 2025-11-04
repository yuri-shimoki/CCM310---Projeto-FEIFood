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
            
            /* [ATENÇÃO]: Em uma aplicação real, seria necessário verificar se
             * se a entrada não constitui um código SQL afim de evitar ataques
             * de injeção de SQL. Estou considerando que este tipo de
             * verificação está fora do escopo do projeto. Portanto, irei
             * omiti-la.
             */
            
            try
            {
                var resultado = UsuarioDao.consultar(new Usuario(nome, senha));
                
                resultado.next();
                if (resultado.getString("nome").equals(nome) &&
                    resultado.getString("senha").equals(senha))
                {
                    var menuPrincipal = new MenuPrincipalController();
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
