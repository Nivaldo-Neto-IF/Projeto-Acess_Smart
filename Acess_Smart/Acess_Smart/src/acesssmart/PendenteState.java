package acesssmart;

public class PendenteState implements ReservationState {
    public void proximoEstado(Reservation reservation) {
        reservation.setState(new ComfirmadaState());
        System.out.println("Reserva confirmada!");
    }
    public void cancelarReserva(Reservation reservation) {
        reservation.setState(new CanceladaState());
        System.out.println("Reserva cancelada!");
    }
}