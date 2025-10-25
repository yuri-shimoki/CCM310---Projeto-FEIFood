package feifood.model;

import java.math.BigDecimal;

/**
 * Classe abstrata que pode ser usada para representar tanto comidas quanto
 * bebidas.
 */
public abstract class Alimento
{
    // valor representado via BigDecimal para evitar erros de precisão.
    private BigDecimal valor;
    private Tipo tipo;
    
    /**
     * Indica o tipo de alimento do objeto atual é: comida ou bebida.
     */
    private enum Tipo
    {
        BEBIDA, COMIDA,
    }
}
