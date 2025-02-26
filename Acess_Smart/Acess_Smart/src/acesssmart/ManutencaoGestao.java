package acesssmart;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ManutencaoGestao {
    private List<Manutencao> manutencoes;
    private int nextId;
    
    public ManutencaoGestao() {
        manutencoes = new ArrayList<>();
        nextId = 1;
    }
    
    public void agendarManutencao(int recursoId, String recursoNome, LocalDateTime inicio, LocalDateTime fim) {
        Manutencao manutencao = new Manutencao(nextId++, recursoId, recursoNome, inicio, fim);
        manutencoes.add(manutencao);
        System.out.println("Manutenção agendada: " + manutencao);
    }
    
    public void registrarManutencao(int recursoId, String recursoNome, int usuarioId, String usuarioNome, String mensagem) {
        System.out.println("Solicitação de manutenção registrada para o recurso " + recursoNome +
                           " pelo usuário " + usuarioNome + ". Mensagem: " + mensagem);
    }
    
    public boolean isEmManutencao(int recursoId, LocalDateTime dateTime) {
        for (Manutencao m : manutencoes) {
            if (m.getRecursoId() == recursoId && m.isInManutencao(dateTime)) {
                return true;
            }
        }
        return false;
    }
    
    public void listarSolicitacoes() {
        if (manutencoes.isEmpty()) {
            System.out.println("Nenhuma manutenção agendada.");
        } else {
            System.out.println("Manutenções agendadas:");
            for (Manutencao m : manutencoes) {
                System.out.println(m);
            }
        }
    }
}
