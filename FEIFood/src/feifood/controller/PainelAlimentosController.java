package feifood.controller;

import feifood.model.Pedido;
import feifood.view.AlimentoItemPanel;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JPanel;

/**
 * Classe que controla a criação do painel de alimentos no menu principal.
 */
public class PainelAlimentosController
{   
    private JPanel painelDeAlimentos;
    private Pedido pedido;
    private ArrayList<AlimentoItemPanel> painelDosAlimentos;
    
    public PainelAlimentosController(Pedido pedido, JPanel painel)
    {
        this.painelDeAlimentos = painel;
        this.pedido = pedido;
        painelDosAlimentos = new ArrayList<AlimentoItemPanel>(pedido.getAlimentos().size());
        
        for (int i = 0; i < pedido.getAlimentos().size(); ++i)
        {    
            painelDeAlimentos.add(getPainelDoAlimento(i));
        }
    }
    
    /**
     * Cria o painel de um alimento no indice i do pedido. Use este método para
     * adicionar alimentos ao painel de alimentos no menu principal.
     * 
     * @param indice indice do alimento cujo painel deve ser criado.
     * @return painel do alimento especificado.
     */
    private AlimentoItemPanel getPainelDoAlimento(int indice)
    {
        painelDosAlimentos.add(new AlimentoItemPanel());
        var painel = painelDosAlimentos.get(indice);
        
        var alimento = pedido.getAlimentos().get(indice);
        
        painel.getNomeLabel().setText(alimento.getNome());
        
        NumberFormat formatoBr = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        painel.getValorLabel().setText(formatoBr.format(alimento.getValor()));
        
        painel.getDetalhesButton().addActionListener(e -> {
            var detalhesFrame = new AlimentoInfoController(alimento);
        });
        
        painel.getPlusButton().addActionListener(e -> {
            var quantidade = pedido.getQuantidades().get(indice);
            pedido.getQuantidades().set(indice, ++quantidade);
            
            painel.getMinusButton().setEnabled(true);
            
            painel.getQuantidadeValorLabel().setText(
                    Integer.toString(quantidade)
            );
        });
        
        painel.getMinusButton().addActionListener(e -> {
            var quantidade = pedido.getQuantidades().get(indice);
            
            if (quantidade > 0)
            {
                if (quantidade == 1)
                    painel.getMinusButton().setEnabled(false);
                
                pedido.getQuantidades().set(indice, --quantidade);
            }
            
            painel.getQuantidadeValorLabel().setText(
                    Integer.toString(quantidade)
            );
            
        });
        
        return painel;
    }
    
    /**
     * Limpa as quantidades de todos os alimentos no pedido
     */
    public void limparQuantidades()
    {
        for (int i = 0; i < pedido.getQuantidades().size(); ++i)
        {
            pedido.getQuantidades().set(i, 0);

            painelDosAlimentos.get(i).getQuantidadeValorLabel().setText("0");
            painelDosAlimentos.get(i).getMinusButton().setEnabled(false);
        }
    }
    
    public JPanel getPainelDeAlimentos()
    {   
        return painelDeAlimentos;
    }
    
    public ArrayList<Integer> getQuantidades()
    {
        return pedido.getQuantidades();
    }
}
