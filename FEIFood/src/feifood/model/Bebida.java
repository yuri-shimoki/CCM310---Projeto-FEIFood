package feifood.model;

import java.math.BigDecimal;

/**
 * Abstrai os dados de uma bebida.
 */
public class Bebida extends Alimento // implements Imposto_Alcool
{
    public Bebida(Integer id, String nome, BigDecimal valor, int tipoCodigo, double litros)
    {
        super(id, nome, valor, tipoCodigo);
    }
}
