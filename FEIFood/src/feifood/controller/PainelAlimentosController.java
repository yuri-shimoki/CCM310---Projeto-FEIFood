package feifood.controller;

import feifood.model.Alimento;
import feifood.model.Pedido;
import feifood.view.AlimentoItemPanel;
import java.awt.Dimension;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JOptionPane;
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
        
        listarTodosAlimentos();
    }
    
    
    /**
     * Lista apenas os alimentos cujo início do nome coincide com a entrada
     * 
     * @param entrada o termo de busca de alimentos dada pelo usuário
     */
    public void buscarEListarAlimento(String entrada)
    {
        var alimentos = pedido.getAlimentos();
        
        painelDeAlimentos.removeAll();
        
        boolean achou = false;
        
        for (int i = 0; i < alimentos.size(); ++i)
        {
            if (alimentos.get(i).getNome().toLowerCase().startsWith(entrada))
            {
                var painel = getPainelDoAlimento(i);
                painel.setPreferredSize(new Dimension(615, 71));
                painel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
                
                painelDeAlimentos.add(painel);
                
                achou = true;
            }
        }
        
        painelDeAlimentos.revalidate();
        painelDeAlimentos.repaint();
        
        if (!achou)
        {
            JOptionPane.showMessageDialog(null,
                                          "Nenhum alimento cujo nome começa com "
                                            + "a entrada especificada foi encontrado.",
                                          "Resultado da Busca",
                                          JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Lista todos os alimentos no painel de alimentos.
     */
    public void listarTodosAlimentos()
    {
        painelDeAlimentos.removeAll();
        
        for (int i = 0; i < pedido.getAlimentos().size(); ++i)
        {    
            painelDeAlimentos.add(getPainelDoAlimento(i));
        }
        
        painelDeAlimentos.revalidate();
        painelDeAlimentos.repaint();
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
        AlimentoItemPanel painel = null;
        
        if (indice >= painelDosAlimentos.size())
        {
            painelDosAlimentos.add(new AlimentoItemPanel());
            painel = painelDosAlimentos.get(indice);

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

                painelDosAlimentos.get(indice).getMinusButton().setEnabled(true);

                painelDosAlimentos.get(indice).getQuantidadeValorLabel().setText(
                        Integer.toString(quantidade)
                );
            });

            painel.getMinusButton().addActionListener(e -> {
                var quantidade = pedido.getQuantidades().get(indice);

                if (quantidade > 0)
                {
                    if (quantidade == 1)
                        painelDosAlimentos.get(indice).getMinusButton().setEnabled(false);

                    pedido.getQuantidades().set(indice, --quantidade);
                }

                painelDosAlimentos.get(indice).getQuantidadeValorLabel().setText(
                        Integer.toString(quantidade)
                );

            });
        }
        else
        {
            painel = painelDosAlimentos.get(indice);
        }
        
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
    
    public ArrayList<Alimento> getAlimentos()
    {
        return pedido.getAlimentos();
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
