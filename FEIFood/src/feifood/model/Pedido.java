package feifood.model;

import java.util.List;

/**
 * Classe que abstrai os dados de um pedido.
 */
public class Pedido
{
    private Usuario usuario;
    private List<Alimento> alimentos;

    public Pedido(Usuario usuario, List<Alimento> alimentos) {
        this.usuario = usuario;
        this.alimentos = alimentos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Alimento> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(List<Alimento> alimentos) {
        this.alimentos = alimentos;
    }
}
