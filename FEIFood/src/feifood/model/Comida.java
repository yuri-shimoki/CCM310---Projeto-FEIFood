package feifood.model;

import java.math.BigDecimal;

/**
 * Abstrai os dados de uma comida.
 */
public class Comida extends Alimento
{

    public Comida(double peso, String nome, BigDecimal valor, Tipo tipo) 
    {
        super(nome, valor, tipo);
    }
}
