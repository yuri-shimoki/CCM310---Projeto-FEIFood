package feifood.model;

import java.util.List;

/**
 * Classe que abstrai os dados de um pedido.
 */
public class Pedido
{
    // Wrapper utilizado para permitir valores nulos
    private Integer id;
    private Usuario usuario;
    private List<Alimento> alimentos;
    private List<Integer> quantidades;

    public Pedido(Integer id, Usuario usuario, List<Alimento> alimentos, List<Integer> quantidades)
    {
        this.id = id;
        this.usuario = usuario;
        this.alimentos = alimentos;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public Usuario getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }

    public List<Alimento> getAlimentos()
    {
        return alimentos;
    }

    public void setAlimentos(List<Alimento> alimentos)
    {
        this.alimentos = alimentos;
    }
    
    public List<Integer> getQuantidades()
    {
        return quantidades;
    }

    public void setQuantidades(List<Integer> quantidades)
    {
        this.quantidades = quantidades;
    }
}
