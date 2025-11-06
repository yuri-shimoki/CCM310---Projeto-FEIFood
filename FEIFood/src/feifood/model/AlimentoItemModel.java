package feifood.model;

/**
 * Classe que controla a quantidade de um alimento em um pedido.
 */
public class AlimentoItemModel
{
    private int quantidade;

    /**
     * Incrementa a <code>quantidade</code> de um alimento.
     */
    public void incrementarQuantidade()
    {
        ++quantidade;
    }
    
    /**
     * Decrementa a <code>quantidade</code> de um alimento.
     */
    public void decrementarQuantidade()
    {
        --quantidade;
    }
    
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade)
    {
        if (quantidade < 0)
        {
            throw new RuntimeException("setQuantidade(quantidade): quantidade "
                + "não pode ser negativa.");
        }
        
        this.quantidade = quantidade;
    }
    
    
}
