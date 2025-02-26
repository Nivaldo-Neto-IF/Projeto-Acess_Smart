package acesssmart;

import java.time.LocalDateTime;

public interface Command {
    void executar();
}
class ComandoGerarRelatorio implements Command {
    private Reports reports;
    private ReservationGerencia reservationgerencia;
    private FeedbackGerencia feedbackGerencia;
    private ResourceGerencia resourcegerencia;
    
    public ComandoGerarRelatorio(Reports reports, 
                                 ReservationGerencia reservationGerencia, 
                                 FeedbackGerencia feedbackManagement, 
                                 ResourceGerencia resourceManagement) {
         this.reports = reports;
         this.reservationgerencia = reservationGerencia;
         this.feedbackGerencia = feedbackManagement;
         this.resourcegerencia = resourceManagement;
    }
    
    public void executar() {
         reports.mostrarMenuRelatorios(reservationgerencia, feedbackGerencia, resourcegerencia);
    }
}

class ComandoRegistrarFeedback implements Command {
    private FeedbackGerencia feedbackGerencia;
    private String feedback;
    private int usuarioId;
    private String usuarioNome; 

    public ComandoRegistrarFeedback(FeedbackGerencia feedbackgerencia, String feedback, int usuarioId, String usuarioNome) {
         this.feedbackGerencia = feedbackgerencia;
         this.feedback = feedback;
         this.usuarioId = usuarioId;
         this.usuarioNome = usuarioNome;
    }

    public void executar() {

         feedbackGerencia.registrarFeedback(feedback, usuarioId, usuarioNome);
    }
}

 class ComandoReservarRecurso implements Command {
    private ReservationGerencia reserva;
    private ResourceGerencia resourceGerencia;
    private int recursoId;
    private int usuarioId;
    private String usuarioNome;
    private LocalDateTime dateTime; 

    public ComandoReservarRecurso(ReservationGerencia reserva, ResourceGerencia resourceGerencia,
                                  int recursoId, int usuarioId, String usuarioNome, LocalDateTime dateTime) {
         this.reserva = reserva;
         this.resourceGerencia = resourceGerencia;
         this.recursoId = recursoId;
         this.usuarioId = usuarioId;
         this.usuarioNome = usuarioNome;
         this.dateTime = dateTime;
    }

    
    public void executar() {
         String recursoNome = resourceGerencia.getRecursoDescricao(recursoId);
         reserva.criarReserva(recursoId, recursoNome, usuarioId, usuarioNome, dateTime);
         System.out.println("Sua reserva foi criada com sucesso");
    }
}

class ComandoSolicitarManutencao implements Command {
    private ManutencaoGestao manutencaoGestao;
    private int recursoId;
    private String recursoNome;
    private int usuarioId;
    private String usuarioNome;
    private String mensagem;
    
    public ComandoSolicitarManutencao(ManutencaoGestao manutencaoGestao, int recursoId, String recursoNome,
                                      int usuarioId, String usuarioNome, String mensagem) {
         this.manutencaoGestao = manutencaoGestao;
         this.recursoId = recursoId;
         this.recursoNome = recursoNome;
         this.usuarioId = usuarioId;
         this.usuarioNome = usuarioNome;
         this.mensagem = mensagem;
    }
    
    public void executar() {
        manutencaoGestao.registrarManutencao(recursoId, recursoNome, usuarioId, usuarioNome, mensagem);
        for (Usuario admin : Facade.getInstance().getUserManager().getAdministradores()) {
            Notificationsi.notifyUser(admin.getId(), "!!! Alerta: Solicitação de manutenção de recurso!!! \"" 
                + recursoNome + "\" (ID: " + recursoId + ") feita por " 
                + usuarioNome + " (ID: " + usuarioId + ")");
        }
   }
}