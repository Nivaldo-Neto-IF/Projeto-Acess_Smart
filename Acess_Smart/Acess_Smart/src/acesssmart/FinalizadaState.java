package acesssmart;

public class FinalizadaState implements ReservationState {
    public void proximoEstado(Reservation reservation) {
        System.out.println("Reserva já finalizada.");
    }
    
    public void cancelarReserva(Reservation reservation) {
        System.out.println("Reserva finalizada não pode ser cancelada.");
    }
}
