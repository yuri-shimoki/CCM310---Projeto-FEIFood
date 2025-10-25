package feifood.model;

import java.math.BigDecimal;

/**
 * Abstrai os dados de uma comida.
 */
public class Comida extends Alimento
{
    private double peso;

    public Comida(double peso, String nome, BigDecimal valor, Tipo tipo) 
    {
        super(nome, valor, tipo);
        this.peso = peso;
    }

    public double getPeso()
    {
        return peso;
    }

    public void setPeso(double peso)
    {
        this.peso = peso;
    }
}
