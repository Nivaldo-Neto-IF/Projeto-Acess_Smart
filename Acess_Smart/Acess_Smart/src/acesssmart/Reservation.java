package acesssmart;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
    private ReservationState state;
    private int usuarioId;
    private int recursoId;
    private String recursoNome;
    private LocalDateTime dateTime; 

    public Reservation(int usuarioId, int recursoId, String recursoNome, LocalDateTime dateTime) {
        this.usuarioId = usuarioId;
        this.recursoId = recursoId;
        this.recursoNome = recursoNome;
        this.dateTime = dateTime;
        this.state = new PendenteState();
    }

    public int getUsuarioId() {
        return usuarioId;
    }
    
    public int getRecursoId() {
        return recursoId;
    }
    
    public String getRecursoNome() {
        return recursoNome;
    }
    
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    
    public void setState(ReservationState state) {
        this.state = state;
    }
    
    public void proximoEstado() {
        state.proximoEstado(this);
    }
    
    public void cancelarReserva() {
        state.cancelarReserva(this);
    }
    
    public String getEstado() {
        if (state instanceof PendenteState) return "Pendente";
        if (state instanceof ComfirmadaState) return "Confirmada";
        if (state instanceof CanceladaState) return "Cancelada";
        if (state instanceof FinalizadaState) return "Finalizada";
        return "Desconhecido";
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Recurso: " + recursoNome + " | Data/Hora: " + dateTime.format(formatter) 
                + " | Estado: " + getEstado();
    }
}
