package acesssmart;

public class RecursoGerador extends Resource {
    private String descricao;

    public RecursoGerador(String descricao) {
        this.descricao = descricao;
    }
    
   
    public String getDescricao() {
        return descricao;
    }
    
    public void exibirInfo() {
        System.out.println(descricao);
    }
}
