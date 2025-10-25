package feifood.model;

import java.math.BigDecimal;

/**
 * Abstrai os dados de uma bebida.
 */
public class Bebida extends Alimento // implements Imposto_Alcool
{
    private double litros;

    public Bebida(String nome, BigDecimal valor, Tipo tipo, double litros)
    {
        super(nome, valor, tipo);
        
        this.litros = litros;
    }

    public double getLitros()
    {
        return litros;
    }

    public void setLitros(double litros)
    {
        this.litros = litros;
    }
}
