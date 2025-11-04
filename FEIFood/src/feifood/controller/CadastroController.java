package feifood.controller;

import feifood.view.CadastroFrame;
import feifood.controller.LoginController;
import feifood.dao.UsuarioDao;
import feifood.model.Usuario;
import java.sql.SQLException;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

/**
 * Classe que controla a lógica de cadastro.
 */
public class CadastroController
{
    private CadastroFrame telaDeCadastro;
    
    public CadastroController()
    {
        telaDeCadastro = new CadastroFrame();
        
        telaDeCadastro.setLocationRelativeTo(null);
        
        telaDeCadastro.getVoltarButton().addActionListener(e -> {
                var loginController = new LoginController();
                telaDeCadastro.dispose();
        });
        
        telaDeCadastro.getCriarContaButton().addActionListener(e -> {
            var nome = telaDeCadastro.getNomeTextField().getText().trim();
            var senha = telaDeCadastro.getSenhaTextField().getText().trim();
            
            if (nome.length() > 20)
            {
                JOptionPane.showMessageDialog(telaDeCadastro,
                    "O nome não pode ter mais que 20 caracteres.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
                
                return;
            }
            
            if (senha.length() > 12)
            {
                JOptionPane.showMessageDialog(telaDeCadastro,
                    "A senha não pode ter mais que 12 caracteres.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
                
                return;
            }
            
            if (nome.length() == 0 || senha.length() == 0)
            {
                JOptionPane.showMessageDialog(telaDeCadastro,
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
                UsuarioDao.inserir(new Usuario(nome, senha));
                
                JOptionPane.showMessageDialog(telaDeCadastro,
                    "O usuário foi cadastrado com sucesso.",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            }
            catch (SQLException e2)
            {
                JOptionPane.showMessageDialog(telaDeCadastro,
                    "Ocorreu um erro ao cadastrar o usuário no "
                        + "banco de dados:\n" + e2.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        
        telaDeCadastro.setVisible(true);
    }
}
