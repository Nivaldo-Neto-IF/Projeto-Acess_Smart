package acesssmart;

public class Feedback {
    private int usuarioId;
    private String usuarioNome;
    private String mensagem;
    
    public Feedback(int usuarioId, String usuarioNome, String mensagem) {
        this.usuarioId = usuarioId;
        this.usuarioNome = usuarioNome;
        this.mensagem = mensagem;
    }
    
    public int getUsuarioId() {
        return usuarioId;
    }
    
    public String getUsuarioNome() {
        return usuarioNome;
    }
    
    public String getMensagem() {
        return mensagem;
    }
}
