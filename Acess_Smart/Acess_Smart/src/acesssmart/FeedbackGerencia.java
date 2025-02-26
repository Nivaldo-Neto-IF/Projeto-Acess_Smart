package acesssmart;

import java.util.ArrayList;
import java.util.List;

public class FeedbackGerencia {
    private List<Feedback> feedbacks = new ArrayList<>();
    
    public void registrarFeedback(String feedback, int usuarioId, String usuarioNome) {
        feedbacks.add(new Feedback(usuarioId, usuarioNome, feedback));
        System.out.println("Feedback registrado!");
    }
    
    public void listarFeedbacks() {
        if (feedbacks.isEmpty()) {
            System.out.println("Nenhum feedback registrado.");
        } 
        else {
            System.out.println("Feedbacks:");
            for (Feedback f : feedbacks) {
                System.out.println("ID: " + f.getUsuarioId() 
                        + " - Nome: " + f.getUsuarioNome() 
                        + " - Feedback: " + f.getMensagem());
            }
        }
    }
    
    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }
}
