package feifood.controller;

import feifood.view.MenuPrincipalFrame;
import feifood.dao.AlimentoDao;
import feifood.model.Alimento;
import feifood.model.Pedido;
import feifood.model.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 */
public class MenuPrincipalController {
    
    private MenuPrincipalFrame telaPrincipal;
    private PainelAlimentosController painelAlimentos;
    
    public MenuPrincipalController(Usuario usuarioAtual)
    {
        telaPrincipal = new MenuPrincipalFrame();
        telaPrincipal.setLocationRelativeTo(null);
        
        try
        {
            ArrayList<Alimento> alimentos = AlimentoDao.obterTodosOsAlimentos();
            var quantidades = new ArrayList<Integer>(alimentos.size());
            
            for (int i = 0; i < alimentos.size(); ++i)
            {
                quantidades.add(0);
            }
            
            painelAlimentos = new PainelAlimentosController(
                    new Pedido(null,
                               usuarioAtual,
                               alimentos,
                               quantidades
                    ),
                    telaPrincipal.getAlimentosPanel()
            );
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(telaPrincipal,
                "Ocorreu um erro ao consultar os alimentos no banco de dados:\n"
                    + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
        
        telaPrincipal.getBuscarButton().addActionListener(e -> {
            var entrada = telaPrincipal.getBuscarTextField()
                                       .getText()
                                       .trim()
                                       .toLowerCase();
            
            painelAlimentos.buscarEListarAlimento(entrada);
        });
        
        telaPrincipal.getCancelarButton().addActionListener(e -> {
            painelAlimentos.listarTodosAlimentos();
        });
        
        telaPrincipal.getConfirmarPedidoButton().addActionListener(e -> {
            boolean pedidoValido = false;
            
            for (var quantidade : painelAlimentos.getQuantidades())
            {
                if (quantidade != 0)
                    pedidoValido = true;
            }
            
            if (!pedidoValido)
            {
                JOptionPane.showMessageDialog(telaPrincipal,
                                          "Seu pedido está vazio.\n"
                                                + "Selecione pelo menos 1 "
                                                + "alimento.",
                                          "Pedido Inválido",
                                          JOptionPane.INFORMATION_MESSAGE
                );
                
                return;
            }
            
            Object[] opcoes = {1, 2, 3, 4, 5};
            int avaliacao = (int) JOptionPane.showInputDialog(
                telaPrincipal,
                "Seu pedido foi entregue.\nPor favor, avalie o serviço:",
                "Pedido Entregue - Avalie o Serviço",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
            );
            
            JOptionPane.showMessageDialog(telaPrincipal,
                                          "Obrigado pela sua avaliação.\n"
                                                + "Você deu " + avaliacao
                                                + " estrelas.",
                                          "Volte Sempre",
                                          JOptionPane.INFORMATION_MESSAGE
            );
            
            painelAlimentos.limparQuantidades();
        });
        
        telaPrincipal.getLimparPedidoButton().addActionListener(e -> {
            painelAlimentos.limparQuantidades();
        });
        
        telaPrincipal.setVisible(true);
    }
}
