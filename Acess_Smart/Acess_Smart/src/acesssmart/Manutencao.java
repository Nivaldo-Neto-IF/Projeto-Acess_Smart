package acesssmart;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Manutencao {
    private int id;
    private int recursoId;
    private String recursoNome;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    
    public Manutencao(int id, int recursoId, String recursoNome, LocalDateTime inicio, LocalDateTime fim) {
        this.id = id;
        this.recursoId = recursoId;
        this.recursoNome = recursoNome;
        this.inicio = inicio;
        this.fim = fim;
    }
    
    public int getId() {
        return id;
    }
    
    public int getRecursoId() {
        return recursoId;
    }
    
    public String getRecursoNome() {
        return recursoNome;
    }
    
    public LocalDateTime getInicio() {
        return inicio;
    }
    
    public LocalDateTime getFim() {
        return fim;
    }
    
    public boolean isInManutencao(LocalDateTime dateTime) {
        return (!dateTime.isBefore(inicio)) && (!dateTime.isAfter(fim));
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Manutenção ID: " + id + " | Recurso: " + recursoNome + " (" + recursoId + ") from " 
                + inicio.format(formatter) + " to " + fim.format(formatter);
    }
}
