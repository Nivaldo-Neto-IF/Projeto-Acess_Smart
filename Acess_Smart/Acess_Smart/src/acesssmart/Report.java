//builder
package acesssmart;

class Report {
    private String titulo;
    private String conteudo;

    public Report(String titulo, String conteudo) {
        this.titulo = titulo;
        this.conteudo = conteudo;
    }

    public void exibirRelatorio() {
        System.out.println("Relatório: " + titulo + "\n" + conteudo);
    }
}