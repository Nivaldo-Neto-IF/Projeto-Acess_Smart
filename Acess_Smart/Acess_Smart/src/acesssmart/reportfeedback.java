package acesssmart;

import java.util.List;

public class reportfeedback extends ReportTemplate {
    private List<Feedback> feedbacks;
    
    public reportfeedback(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
    
    protected void coletarDados() {
        for (Feedback f : feedbacks) {
            dados.append("ID: ").append(f.getUsuarioId())
                 .append(" - Nome: ").append(f.getUsuarioNome())
                 .append(" - Feedback: ").append(f.getMensagem())
                 .append("\n");
        }
    }
    
    protected void processarDados() {
        relatorioFormatado = "Relatório de Feedbacks\n" + dados.toString();
    }
}
