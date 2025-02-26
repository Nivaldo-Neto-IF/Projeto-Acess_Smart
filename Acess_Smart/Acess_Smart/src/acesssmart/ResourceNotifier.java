package acesssmart;

import java.util.ArrayList;
import java.util.List;

public class ResourceNotifier {
    private List<Observer> observers = new ArrayList<>();
    
    public void adicionarObservador(Observer observer) {
        observers.add(observer);
    }
    
    public void removerObservador(Observer observer) {
        observers.remove(observer);
    }
    
    public void notificar(String mensagem) {
        for (Observer observer : observers) {
            observer.atualizar(mensagem);
        }
    }
    
    public void listarObservadores() {
        System.out.println("Observadores cadastrados:");
        for (Observer observer : observers) {
            System.out.println("- " + ((Usuario) observer).getNome());
        }
    }
}
