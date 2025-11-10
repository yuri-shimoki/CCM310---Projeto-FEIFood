package feifood.model;

import java.math.BigDecimal;

/**
 * Abstrai os dados de uma bebida.
 */
public class Bebida extends Alimento implements Imposto_Alcool
{
    BigDecimal impostoAlcool;
    
    public Bebida(Integer id, String nome, BigDecimal valor, int tipoCodigo, BigDecimal impostoAlcool)
    {
        super(id, nome, valor, tipoCodigo);
        this.impostoAlcool = impostoAlcool;
    }
    
    /**
     * Método abstrato que simboliza a aplicação do imposto do álcool sobre o
     * valor da bebida.
     * 
     * @param valor o valor da bebida.
     * @return valor da bebida após aplicação de imposto do álcool.
     */
    @Override
    public BigDecimal getValorComImposto()
    {
        return this.getValor().multiply(BigDecimal.ONE.add(impostoAlcool));
    }

    public BigDecimal getImpostoAlcool()
    {
        return impostoAlcool;
    }

    public void setImpostoAlcool(BigDecimal impostoAlcool)
    {
        if (impostoAlcool.compareTo(BigDecimal.ZERO) < 0)
        {
            throw new RuntimeException("O imposto do álcool não pode ser negativo.");
        }
        
        this.impostoAlcool = impostoAlcool;
    }
}
