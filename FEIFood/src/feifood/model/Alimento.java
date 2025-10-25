package feifood.model;

import java.math.BigDecimal;

/**
 * Classe abstrata que pode ser usada para representar tanto comidas quanto
 * bebidas.
 */
public abstract class Alimento
{
    // valor representado via BigDecimal para evitar erros de precisão.
    private String nome;
    private BigDecimal valor;
    private Tipo tipo;
    
    public Alimento(String nome, BigDecimal valor, Tipo tipo)
    {
        this.nome = nome;
        this.valor = valor;
        this.tipo = tipo;
    }
    
    /**
     * Indica o tipo de alimento do objeto atual é: comida ou bebida.
     */
    public enum Tipo
    {
        BEBIDA, COMIDA,
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public BigDecimal getValor()
    {
        return valor;
    }

    public void setValor(BigDecimal valor)
    {
        this.valor = valor;
    }

    public Tipo getTipo()
    {
        return tipo;
    }

    public void setTipo(Tipo tipo)
    {
        this.tipo = tipo;
    }
}
