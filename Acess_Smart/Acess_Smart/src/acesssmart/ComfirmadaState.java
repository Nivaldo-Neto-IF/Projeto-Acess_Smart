package acesssmart;
public class ComfirmadaState implements ReservationState {
    public void proximoEstado(Reservation reservation) {
        System.out.println("Reserva já confirmada.");
    }
    public void cancelarReserva(Reservation reservation) {
        reservation.setState(new CanceladaState());
        System.out.println("Reserva cancelada!");
    }
}