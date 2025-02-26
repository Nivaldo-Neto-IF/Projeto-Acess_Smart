package acesssmart;

public abstract class ReportTemplate {
    protected StringBuilder dados = new StringBuilder();
    protected String relatorioFormatado = "";
    
    public final void gerarRelatorio() {
        iniciarRelatorio();
        coletarDados();
        processarDados();
        exibirRelatorio();
    }
    
    protected void iniciarRelatorio() {
        System.out.println("\n--- Iniciando relatório ---");
    }
    
    protected abstract void coletarDados();
    protected abstract void processarDados();
    
    protected void exibirRelatorio() {
        System.out.println(relatorioFormatado);
    }
}
