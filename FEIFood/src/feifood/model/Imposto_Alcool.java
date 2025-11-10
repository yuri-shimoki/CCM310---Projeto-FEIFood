package feifood.model;

import java.math.BigDecimal;

/**
 * Interface que simboliza a aplicação do imposto do álcool.
 * Bebidas não alcoólicas podem retornar o valor sem alterações.
 */
public interface Imposto_Alcool
{
    /**
     * Método abstrato que simboliza a aplicação do imposto do álcool sobre o
     * valor da bebida.
     * 
     * @param valor o valor da bebida.
     * @return valor da bebida após aplicação de imposto do álcool.
     */
    BigDecimal getValorComImposto();
}
