package acesssmart;

import java.util.ArrayList;
import java.util.List;

public class Usuario implements Observer {
    private int id;
    private String nome;
    private String login;
    private String senha;
    private boolean isAdmin;
    private List<String> notificacoes; 

    public Usuario(int id, String nome, String login, String senha, boolean isAdmin) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.isAdmin = isAdmin;
        this.notificacoes = new ArrayList<>();
    }

    public void atualizar(String mensagem) {
        notificacoes.add(mensagem);
    }

    public void exibirNotificacoes() {
        if (notificacoes.isEmpty()) {
            System.out.println("Nenhuma notificação.");
        } else {
            System.out.println("Notificações:");
            for (String msg : notificacoes) {
                System.out.println(msg);
            }
            notificacoes.clear(); 
        }
    }

    public boolean autenticar(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }
}
