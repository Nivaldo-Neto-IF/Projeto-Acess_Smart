package acesssmart;

import java.util.Map;

public class reportutilizacao extends ReportTemplate {
    private Map<String, Integer> usoRecursos;
    private int totalFeedbacks;
    
    public reportutilizacao(Map<String, Integer> usoRecursos, int totalFeedbacks) {
        this.usoRecursos = usoRecursos;
        this.totalFeedbacks = totalFeedbacks;
    }

    protected void coletarDados() {
        for (Map.Entry<String, Integer> entry : usoRecursos.entrySet()) {
            dados.append(entry.getKey())
                 .append(": ")
                 .append(entry.getValue())
                 .append(" usos no mês\n");
        }
        dados.append("Total de Feedbacks: ").append(totalFeedbacks).append("\n");
    }

    protected void processarDados() {
        relatorioFormatado = "Relatório de Uso de Recursos\n" + dados.toString();
    }
}
