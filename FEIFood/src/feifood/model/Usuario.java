package feifood.model;

/**
 * Classe que abstrai os dados de um usuário.
 */
public class Usuario
{
    // Wrapper utilizado para permitir valores nulos
    private Integer id;
    private String nome;
    private String senha;
    
    public Usuario(Integer id, String nome, String senha)
    {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome.strip();
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {   
        this.senha = senha.strip();
    }
}
