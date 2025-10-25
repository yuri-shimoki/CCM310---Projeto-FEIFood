package feifood.model;

import javax.swing.JFrame;

/**
 * Classe responsável por conter a lógica de negócio da aplicação.
 * 
 * @author Yuri
 */
public class ApplicationModel
{
    private JFrame janelaAtual;
    
    public ApplicationModel(JFrame janelaInicial)
    {
        janelaAtual = janelaInicial;
    }

    /**
     * Altera o valor de <code>janelaAtual</code> para <code>novaJanela</code>.
     * 
     * @param novaJanela a nova janela a se tornar a principal da aplicação.
     */
    public void mudarJanela(JFrame novaJanela)
    {
        janelaAtual = novaJanela;
    }
    
    /**
     * Função responsável por determinar a visibilidade da <code>janelaAtual</code>.
     * 
     * @param visivel determina se a janela deve se tornar visível ou não.
     */
    public void setVisibilidadeDaJanela(boolean visivel)
    {
        janelaAtual.setVisible(visivel);
    }
    
    public JFrame getJanelaAtual()
    {
        return janelaAtual;
    }

    public void setJanelaAtual(JFrame janelaAtual)
    {
        this.janelaAtual = janelaAtual;
    }
}
