package feifood.model;

import java.util.ArrayList;

/**
 * Classe que abstrai os dados de um pedido.
 */
public class Pedido
{
    // Wrapper utilizado para permitir valores nulos
    private Integer id;
    private Usuario usuario;
    private ArrayList<Alimento> alimentos;
    private ArrayList<Integer> quantidades;

    public Pedido(Integer id,
                  Usuario usuario,
                  ArrayList<Alimento> alimentos,
                  ArrayList<Integer> quantidades)
    {
        this.id = id;
        this.usuario = usuario;
        this.alimentos = alimentos;
        this.quantidades = quantidades;
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

    public ArrayList<Alimento> getAlimentos()
    {
        return alimentos;
    }

    public void setAlimentos(ArrayList<Alimento> alimentos)
    {
        this.alimentos = alimentos;
    }
    
    public ArrayList<Integer> getQuantidades()
    {
        return quantidades;
    }

    public void setQuantidades(ArrayList<Integer> quantidades)
    {
        this.quantidades = quantidades;
    }
}
