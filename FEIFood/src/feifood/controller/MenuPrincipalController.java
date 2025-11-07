package feifood.controller;

import feifood.view.MenuPrincipalFrame;
import feifood.dao.AlimentoDao;
import feifood.model.Alimento;
import feifood.model.Pedido;
import feifood.model.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
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
        
        });
        
        telaPrincipal.getConfirmarPedidoButton().addActionListener(e -> {
        
        });
        
        telaPrincipal.getLimparPedidoButton().addActionListener(e -> {
            painelAlimentos.limparQuantidades();
        });
        
        telaPrincipal.setVisible(true);
    }
}
