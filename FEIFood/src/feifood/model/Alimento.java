package feifood.model;

import java.math.BigDecimal;

/**
 * Classe abstrata que pode ser usada para representar tanto comidas quanto
 * bebidas.
 */
public class Alimento
{
    private int id;
    private String nome;
    private BigDecimal valor; // valor representado via BigDecimal para evitar
                              // erros de precisão.
    private Tipo tipo;
    
    public Alimento(int id, String nome, BigDecimal valor, int tipoCodigo)
    {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.tipo = Tipo.codigoParaTipo(tipoCodigo);
    }
    
    /**
     * Indica o tipo de alimento do objeto atual é: comida ou bebida.
     */
    public enum Tipo
    {
        BEBIDA(0), COMIDA(1);
        
        private final int codigo;
        
        Tipo(int codigo)
        {
            this.codigo = codigo;
        }
        
        public int getCodigo()
        {
            return codigo;
        }
        
        public static Tipo codigoParaTipo(int codigo) {
            for (Tipo tipo : values())
            {
                if (tipo.codigo == codigo)
                    return tipo;
            }
            
            throw new IllegalArgumentException("Código desconhecido: " + codigo);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (valor.compareTo(BigDecimal.ZERO) < 0)
        {
            throw new RuntimeException("valor em Alimento.setValor("+ valor +")"
                + "não pode ser negativo.");
        }
        
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
