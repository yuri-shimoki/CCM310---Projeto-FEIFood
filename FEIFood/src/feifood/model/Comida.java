package feifood.model;

import java.math.BigDecimal;

/**
 * Abstrai os dados de uma comida.
 */
public class Comida extends Alimento
{
    public Comida(Integer id, double peso, String nome, BigDecimal valor, int tipoCodigo) 
    {
        super(id, nome, valor, tipoCodigo);
    }
}
