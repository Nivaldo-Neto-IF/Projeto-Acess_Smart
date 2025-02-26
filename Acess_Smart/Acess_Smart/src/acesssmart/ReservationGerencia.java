package acesssmart;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ReservationGerencia {
    private Map<Integer, Reservation> reservas;
    private int nextId;
    private ManutencaoGestao manutencaoGestao;
    
    public ReservationGerencia(ManutencaoGestao manutencaoGestao) {
        reservas = new HashMap<>();
        nextId = 1;
        this.manutencaoGestao = manutencaoGestao;
    }
    
    public int criarReserva(int recursoId, String recursoNome, int usuarioId, String usuarioNome, LocalDateTime dateTime) {
        if (manutencaoGestao.isEmManutencao(recursoId, dateTime)) {
            System.out.println("O recurso está em manutenção no horário escolhido.");
            return -1;
        }
        Reservation reserva = new Reservation(usuarioId, recursoId, recursoNome, dateTime);
        reservas.put(nextId, reserva);
        
        for (Usuario admin : Facade.getInstance().getUserManager().getAdministradores()) {
            Notificationsi.notifyUser(admin.getId(), "Nova reserva criada para o recurso " + recursoNome +
                " pelo usuário " + usuarioNome + " (Reserva ID: " + nextId + ")");
        }
        return nextId++;
    }



    
    public void consultarReservasUsuario(int usuarioId) {
        Usuario usuario = Facade.getInstance().getUserManager().getUsuarioById(usuarioId);
        String nome = (usuario != null) ? usuario.getNome() : "Desconhecido";
        System.out.println("Reservas do usuário " + nome + " (ID: " + usuarioId + "):");
        for (Map.Entry<Integer, Reservation> entry : reservas.entrySet()) {
            if (entry.getValue().getUsuarioId() == usuarioId) {
                System.out.println("Reserva ID: " + entry.getKey() + " - " + entry.getValue());
            }
        }
    }
    public void consultarTodasReservas() {
        System.out.println("Todas as reservas:");
        for (Map.Entry<Integer, Reservation> entry : reservas.entrySet()) {
            System.out.println("ID: " + entry.getKey() + " - " + entry.getValue());
        }
    }
    
    public Map<Integer, Reservation> getReservas() {
        return reservas;
    }
    
    public void alterarEstadoReserva(int reservaId, int novoEstado) throws Exception {
        Reservation reserva = reservas.get(reservaId);
        if (reserva == null) {
            throw new Exception("Reserva não encontrada.");
        }
        switch (novoEstado) {
            case 1:
                reserva.setState(new FinalizadaState());
                break;
            case 2:
                reserva.setState(new ComfirmadaState());
                break;
            case 3:
                reserva.setState(new CanceladaState());
                break;
            default:
                throw new Exception("Estado inválido.");
        }
        System.out.println("Estado da reserva " + reservaId + " alterado para " + reserva.getEstado());
        Usuario usuario = Facade.getInstance().getUserManager().getUsuarioById(reserva.getUsuarioId());
        if (usuario != null) {
            String mensagemNotificacao = "Olá, " + usuario.getNome() + ", sua reserva " + reservaId +
                " para o dia " + reserva.getDateTime().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                + " agora está " + reserva.getEstado() + ".";
            Notificationsi.notifyUser(usuario.getId(), mensagemNotificacao);
        } else {
            Notificationsi.notifyUser(reserva.getUsuarioId(), "Sua reserva " + reservaId + " agora está " + reserva.getEstado() + ".");
        }
    }

    
    public void finalizarReserva(int reservaId) throws Exception {
        Reservation reserva = reservas.get(reservaId);
        if (reserva == null) {
            throw new Exception("Reserva não encontrada.");
        }
        reserva.setState(new FinalizadaState());
        System.out.println("Reserva " + reservaId + " finalizada.");
        Usuario usuario = Facade.getInstance().getUserManager().getUsuarioById(reserva.getUsuarioId());
        String mensagem = "";
        if (usuario != null) {
            mensagem = "Reserva " + reservaId + " finalizada pelo usuário " +
                usuario.getNome() + " (ID: " + usuario.getId() + ")";
        } else {
            mensagem = "Reserva " + reservaId + " finalizada.";
        }
        for (Usuario admin : Facade.getInstance().getUserManager().getAdministradores()) {
            Notificationsi.notifyUser(admin.getId(), mensagem);
        }
    }
}