package acesssmart;
class ReportBuilder {
    private String titulo;
    private String conteudo;

    public ReportBuilder setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public ReportBuilder setConteudo(String conteudo) {
        this.conteudo = conteudo;
        return this;
    }

    public Report build() {
        return new Report(titulo, conteudo);
    }
}