package acesssmart;

import java.time.LocalDateTime;

public class Facade {
    private static Facade instance;
    private UserManager userManager;
    private ResourceGerencia resourceGerencia;
    private ReservationGerencia reservationGerencia;
    private FeedbackGerencia feedbackGerencia;
    private ManutencaoGestao manutencaoGestao;
    private Reports reports;
    private Invocador invocador;
    
    private Facade() {
        userManager = new UserManager();
        resourceGerencia = new ResourceGerencia();
        feedbackGerencia = new FeedbackGerencia();
        manutencaoGestao = new ManutencaoGestao();
        reservationGerencia = new ReservationGerencia(manutencaoGestao);
        reports = Reports.getInstance();
        invocador = new Invocador();
    }
    
    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }
    
    public Usuario login(String login, String senha) throws Exception {
        Usuario usuario = userManager.autenticar(login, senha);
        if (usuario == null) {
            throw new Exception("Login inválido.");
        }
        Notificationsi.registerObserver(usuario);
        return usuario;
    }
    
    public void registrarUsuario(String nome, String login, String senha, boolean isAdmin) throws Exception {
        if (userManager.usuarioExiste(login)) {
            throw new Exception("Usuário já registrado!");
        }
        int id = userManager.getNextId();
        Usuario novoUsuario = new Usuario(id, nome, login, senha, isAdmin);
        userManager.adicionarUsuario(novoUsuario);
        System.out.println("Usuário " + nome + " registrado com sucesso!");
    }
    
    public void gerarRelatorio() {
        invocador.adicionarComando(new ComandoGerarRelatorio(reports, reservationGerencia, feedbackGerencia, resourceGerencia));
    }
    
    public void adicionarRecurso(String descricao) {
        resourceGerencia.adicionarRecurso(descricao);
    }
    
    public void consultarRecursos() {
        resourceGerencia.consultarRecursos();
    }
    
    public void consultarTodasReservas() {
        reservationGerencia.consultarTodasReservas();
    }
    
    public void alterarEstadoReserva(int reservaId, int novoEstado) {
        try {
            reservationGerencia.alterarEstadoReserva(reservaId, novoEstado);
        } catch (Exception e) {
            System.out.println("Erro ao alterar estado: " + e.getMessage());
        }
    }
    
    public void fazerReserva(int usuarioId, int recursoId, LocalDateTime dateTime) {
        String aviso = resourceGerencia.getAvisoManutencao(recursoId);
        if (aviso != null) {
            System.out.println("Este recurso está em manutenção e não pode ser reservado. " + aviso);
            return;
        }
        Usuario usuario = userManager.getUsuarioById(usuarioId);
        invocador.adicionarComando(new ComandoReservarRecurso(
                reservationGerencia, resourceGerencia, recursoId, usuarioId, usuario.getNome(), dateTime));
    }
    
    public void consultarReservasUsuario(int usuarioId) {
        reservationGerencia.consultarReservasUsuario(usuarioId);
    }
    
    public void registrarFeedback(String feedback, int usuarioId) {
        Usuario usuario = userManager.getUsuarioById(usuarioId);
        String usuarioNome = usuario != null ? usuario.getNome() : "";
        invocador.adicionarComando(new ComandoRegistrarFeedback(feedbackGerencia, feedback, usuarioId, usuarioNome));
    }
    
    public void solicitarManutencao(int usuarioId, int recursoId, String mensagem) {
        Usuario usuario = userManager.getUsuarioById(usuarioId);
        String usuarioNome = usuario != null ? usuario.getNome() : "";
        String recursoNome = resourceGerencia.getRecursoDescricao(recursoId);
        invocador.adicionarComando(new ComandoSolicitarManutencao(manutencaoGestao, recursoId, recursoNome, usuarioId, usuarioNome, mensagem));
    }
    
    public void finalizarReserva(int reservaId) {
        try {
            reservationGerencia.finalizarReserva(reservaId);
        } catch (Exception e) {
            System.out.println("Erro ao finalizar reserva: " + e.getMessage());
        }
    }
    
    public void consultarManutencao() {
        manutencaoGestao.listarSolicitacoes();
    }
    
    public void gerarAgendamentoManutencao(int recursoId, LocalDateTime inicio, LocalDateTime fim) {
        String recursoNome = resourceGerencia.getRecursoDescricao(recursoId);
        manutencaoGestao.agendarManutencao(recursoId, recursoNome, inicio, fim);
    }
    
    public void executarComandos() {
        invocador.executarComandos();
    }
    
    public void listarUsuarios() {
        userManager.listarUsuarios();
    }
    
    public void tornarUsuarioAdminPorId(int id) throws Exception {
        Usuario usuario = userManager.getUsuarioById(id);
        if (usuario == null) {
            throw new Exception("Usuário não encontrado!");
        }
        usuario.setAdmin(true);
        System.out.println("Usuário " + usuario.getNome() + " (ID: " + usuario.getId() + ") agora é administrador!");
    }
    
    public UserManager getUserManager() {
        return userManager;
    }
    
    public void gerenciarManutencaoRecurso(int recursoId, int opcao, String aviso) {
        if (opcao == 1) {
            resourceGerencia.setAvisoManutencao(recursoId, aviso);
        } else if (opcao == 2) {
            resourceGerencia.removeAvisoManutencao(recursoId);
        } else {
            System.out.println("Opção inválida para gerenciamento de manutenção.");
        }
    }
}
