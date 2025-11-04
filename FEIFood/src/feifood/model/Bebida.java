package feifood.model;

import java.math.BigDecimal;

/**
 * Abstrai os dados de uma bebida.
 */
public class Bebida extends Alimento // implements Imposto_Alcool
{
    public Bebida(String nome, BigDecimal valor, Tipo tipo, double litros)
    {
        super(nome, valor, tipo);
    }
}
