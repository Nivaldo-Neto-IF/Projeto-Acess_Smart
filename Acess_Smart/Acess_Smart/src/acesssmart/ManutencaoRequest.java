package acesssmart;

public class ManutencaoRequest {
    private int requestId;
    private int recursoId;
    private String recursoNome;
    private int usuarioId;
    private String usuarioNome;
    private String mensagem;
    
    public ManutencaoRequest(int requestId, int recursoId, String recursoNome, int usuarioId, String usuarioNome, String mensagem) {
        this.requestId = requestId;
        this.recursoId = recursoId;
        this.recursoNome = recursoNome;
        this.usuarioId = usuarioId;
        this.usuarioNome = usuarioNome;
        this.mensagem = mensagem;
    }
    
    public int getRequestId() {
        return requestId;
    }
    
    public int getRecursoId() {
        return recursoId;
    }
    
    public String getRecursoNome() {
        return recursoNome;
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
