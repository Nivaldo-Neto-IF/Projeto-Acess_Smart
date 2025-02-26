package acesssmart;

import java.util.Map;
import java.util.Scanner;

public class Reports {
    private static Reports instance;
    private static Scanner input = new Scanner(System.in);
    
    private Reports() {}
    
    public static Reports getInstance() {
        if(instance == null) {
            instance = new Reports();
        }
        return instance;
    }
    
    public void mostrarMenuRelatorios(ReservationGerencia reservationGerencia, 
                                        FeedbackGerencia feedbackGerencia, 
                                        ResourceGerencia resourceGerencia) {
        while(true) {
            System.out.println("\n\tGerência de Relatórios\n");
            System.out.println("1. Relatório de Uso");
            System.out.println("2. Relatório de Feedbacks");
            System.out.println("3. Voltar");
            
            int opcao = confereentrada(input, "\nEscolha uma opção: ");
            
            switch(opcao) {
                case 1:
                    gerarRelatorioUso(reservationGerencia, feedbackGerencia);
                    break;
                case 2:
                    gerarRelatorioFeedbacks(feedbackGerencia);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private void gerarRelatorioUso(ReservationGerencia reservationGerencia, FeedbackGerencia feedbackGerencia) {
        Map<Integer, Reservation> reservas = reservationGerencia.getReservas();
        java.util.Map<String, Integer> usoRecursos = new java.util.HashMap<>();
        for (Reservation r : reservas.values()) {
            String rec = r.getRecursoNome();
            usoRecursos.put(rec, usoRecursos.getOrDefault(rec, 0) + 1);
        }
        int totalFeedbacks = feedbackGerencia.getFeedbacks().size();
        
        ReportTemplate relatorioUso = new reportutilizacao(usoRecursos, totalFeedbacks);
        relatorioUso.gerarRelatorio();
    }
    
    private void gerarRelatorioFeedbacks(FeedbackGerencia feedbackGerencia) {
        ReportTemplate relatorioFeedback = new reportfeedback(feedbackGerencia.getFeedbacks());
        relatorioFeedback.gerarRelatorio();
    }
    
    public static int confereentrada(Scanner input, String mensagem) {
        System.out.print(mensagem);
        while (!input.hasNextInt()) {
            System.out.println("!Entrada inválida! Digite um número.");
            input.next();
            System.out.print(mensagem);
        }
        int valor = input.nextInt();
        input.nextLine();
        return valor;
    }
}
