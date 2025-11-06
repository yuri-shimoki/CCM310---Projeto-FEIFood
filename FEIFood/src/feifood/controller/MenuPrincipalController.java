package feifood.controller;

import feifood.view.MenuPrincipalFrame;
import feifood.dao.AlimentoDao;
import feifood.model.Alimento;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 */
public class MenuPrincipalController {
    
    private MenuPrincipalFrame telaPrincipal;
    
    public MenuPrincipalController()
    {
        telaPrincipal = new MenuPrincipalFrame();
        telaPrincipal.setLocationRelativeTo(null);
        
        try
        {
            ArrayList<Alimento> alimentos = AlimentoDao.obterTodosOsAlimentos();
        
            for (var alimento : alimentos)
            {
                var painel = new AlimentoItemController(alimento).getAlimentoItemPanel();
                telaPrincipal.getAlimentosPanel().add(painel);
            }
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
        
        });
        
        telaPrincipal.setVisible(true);
    }
}
