package acesssmart;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Facade facade = Facade.getInstance();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        while (true) {
            System.out.println("\n=== Acess Smart ===");
            System.out.println("\n=== Bem Vindo ===");
            System.out.println("1. Login");
            System.out.println("2. Registrar novo usuário");
            
            int opcaoInicial = confereentrada(input, "\nEscolha uma opção: ");

            if (opcaoInicial == 2) {
                System.out.print("Nome: ");
                String nome = input.nextLine();
                System.out.print("Login: ");
                String login = input.nextLine();
                System.out.print("Senha: ");
                String senha = input.nextLine();
                try {
                    facade.registrarUsuario(nome, login, senha, false);
                } 
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                continue;
            } 
            else if (opcaoInicial != 1) {
                System.out.println("Opção inválida!");
                continue;
            }
            
            Usuario usuarioLogado = null;
            boolean tentarNovamente = true;
            while (tentarNovamente && usuarioLogado == null) {
                System.out.print("Login: ");
                String login = input.next();
                System.out.print("Senha: ");
                String senha = input.next();
                try {
                    usuarioLogado = facade.login(login, senha);
                } 
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                if (usuarioLogado == null) {
                    System.out.println("Login inválido.");
                    System.out.print("Deseja tentar novamente? (s/n): ");
                    String resposta = input.next();
                    if (resposta.equalsIgnoreCase("n")) {
                        tentarNovamente = false;
                    }
                }
            }
            if (usuarioLogado == null) {
                continue;
            }
            
            System.out.println("\nBem-vindo, " + usuarioLogado.getNome() + "!");
            usuarioLogado.exibirNotificacoes();
            
            boolean sairSessao = false;
            while (!sairSessao) {
            	
                if (usuarioLogado.isAdmin()) {
                    System.out.println("\n=== Menu Admin ===");
                    System.out.println("1. Adicionar Recurso");
                    System.out.println("2. Consultar Recursos");
                    System.out.println("3. Consultar todas as reservas");
                    System.out.println("4. Alterar estado de uma reserva");
                    System.out.println("5. Gerar Relatório");
                    System.out.println("6. Tornar usuário admin");
                    System.out.println("7. Manutenção do Recurso");
                    System.out.println("8. Consultar Pedidos de Manutenção");
                    System.out.println("0. Sair");
                    
                    int opcaoAdmin = confereentrada(input, "\nEscolha uma opção: ");
                    
                    switch (opcaoAdmin) {
                    
                        case 1:
                        	
                            System.out.print("Digite o tipo do recurso (ex: rampa, elevador, etc.): ");
                            String tipo = input.nextLine();
                            if (tipo.trim().isEmpty()) {
                                System.out.println("Nenhum tipo foi informado.");
                            }
                            else {
                                facade.adicionarRecurso(tipo);
                                facade.executarComandos();
                            }
                            break;
                            
                        case 2:
                        	
                            facade.consultarRecursos();
                            facade.executarComandos();
                            break;
                            
                        case 3:
                        	
                            facade.consultarTodasReservas();
                            facade.executarComandos();
                            break;
                            
                        case 4:
                        	
                            facade.consultarTodasReservas();
                            facade.executarComandos();
                            System.out.print("Digite o ID da reserva que deseja alterar: ");
                            int reservaId = confereentrada(input, "\nDigite o ID: ");
                            System.out.print("Atualização de Status (1: Finalizada, 2: Confirmada, 3: Cancelada): ");
                            int nestado = confereentrada(input, "\nDigite o novo estado: ");
                            if (nestado < 1 || nestado > 3) {
                                System.out.println("Opção inválida! Apenas 1, 2 ou 3 são permitidos.");
                            }
                            else {
                                facade.alterarEstadoReserva(reservaId, nestado);
                                facade.executarComandos();
                            }
                            break;
                            
                        case 5:

                            facade.gerarRelatorio();
                            facade.executarComandos();
                            break;
                            
                        case 6:
                        	
                            facade.listarUsuarios();
                            System.out.print("Digite o ID do usuário que deseja promover a admin: ");
                            int idUsuario = confereentrada(input, "\nDigite o ID: ");
                            try {
                                facade.tornarUsuarioAdminPorId(idUsuario);
                            }
                            catch (Exception e) {
                            	System.out.println(e.getMessage());
                            }
                            break;
                            
                        case 7:
                        	
                            mostrarMenuManutencao(input, facade);
                            break;
                            
                        case 8:
                        	
                            facade.consultarManutencao();
                            break;
                            
                        case 0:
                        	
                            sairSessao = true;
                            break;
                            
                        default:
                        	
                            System.out.println("Opção inválida.");
                    }
                } 
                else {
                    System.out.println("\n--- Menu ---");
                    System.out.println("1. Consultar minhas reservas");
                    System.out.println("2. Fazer nova reserva");
                    System.out.println("3. Registrar Feedback / Solicitar Manutenção");
                    System.out.println("4. Finalizar Reserva");
                    System.out.println("0. Sair");
                    
                    int opcaoUser = confereentrada(input, "\nEscolha uma opção: ");
                    
                    switch (opcaoUser) {
                    
                        case 1:
                        	
                            facade.consultarReservasUsuario(usuarioLogado.getId());
                            facade.executarComandos();
                            break;
                            
                        case 2:
                        	
                            facade.consultarRecursos();
                            System.out.print("Escolha o ID do recurso: ");
                            int recursoId = confereentrada(input, "\nDigite o ID: ");
                            System.out.print("Digite a data e hora da reserva (dd/MM/AAAA HH:mm): ");
                            String dataHoraStr = input.nextLine();
                            try {
                                LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, formatter);
                                facade.fazerReserva(usuarioLogado.getId(), recursoId, dataHora);
                                facade.executarComandos();
                            }
                            catch (Exception e) {
                                System.out.println("Formato de data/hora inválido.");
                            }
                            break;
                            
                        case 3:
                        	
                            System.out.println("1. Registrar FeedBack");
                            System.out.println("2. Solicitar Manutenção");
                            int opcaofeedmanu = confereentrada(input, "\nEscolha uma opção: ");
                            if (opcaofeedmanu == 1) {
                                System.out.print("Digite seu feedback: ");
                                String feedback = input.nextLine();
                                facade.registrarFeedback(feedback, usuarioLogado.getId());
                                facade.executarComandos();
                            } 
                            else if (opcaofeedmanu == 2) {
                                facade.consultarRecursos();
                                System.out.print("Escolha o ID do recurso para manutenção: ");
                                int recursoManut = confereentrada(input, "\nDigite o ID: ");
                                System.out.print("Motivo da Solicitação: ");
                                String mensagem = input.nextLine();
                                facade.solicitarManutencao(usuarioLogado.getId(), recursoManut, mensagem);
                                facade.executarComandos();
                            } 
                            else {
                                System.out.println("Opção inválida.");
                            }
                            break;
                            
                        case 4:
                        	
                            facade.consultarReservasUsuario(usuarioLogado.getId());
                            System.out.print("Digite o ID da sua reserva a ser finalizada: ");
                            int idFinalizarUser = confereentrada(input, "\nDigite o ID: ");
                            facade.finalizarReserva(idFinalizarUser);
                            break;
                            
                        case 0:
                        	
                            sairSessao = true;
                            break;
                            
                        default:
                            System.out.println("Opção inválida.");
                    }
                }
            }
        }
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

    public static void mostrarMenuManutencao(Scanner input, Facade facade) {
        boolean sair = false;
        while (!sair) {
        	
            System.out.println("\n=== Menu de Manutenção ===");
            System.out.println("1. Definir aviso de manutenção");
            System.out.println("2. Remover aviso de manutenção");
            System.out.println("3. Agendar manutenção");
            System.out.println("0. Voltar");
            int opcao = confereentrada(input, "\nEscolha uma opção: ");
            switch (opcao) {
            
                case 1:
                    facade.consultarRecursos();
                    System.out.print("Digite o ID do recurso: ");
                    int idRecurso = confereentrada(input, "\nDigite o ID: ");
                    System.out.print("Deixe uma mensagem se o campo ficar vazio ficara a mensgem automatica 'Em manutenção'): ");
                    String aviso = input.nextLine();
                    facade.gerenciarManutencaoRecurso(idRecurso, 1, aviso);
                    break;
                    
                case 2:
                    facade.consultarRecursos();
                    System.out.print("Digite o ID do recurso: ");
                    int idRecurso2 = confereentrada(input, "\nDigite o ID: ");
                    facade.gerenciarManutencaoRecurso(idRecurso2, 2, "");
                    break;
                    
                case 3:
                    facade.consultarRecursos();
                    System.out.print("Digite o ID do recurso: ");
                    int idRec = confereentrada(input, "\nDigite o ID: ");
                    System.out.print("Digite a data e hora de início (dd/MM/AAAA HH:mm): ");
                    String inicioStr = input.nextLine();
                    System.out.print("Digite a data e hora de término (dd/MM/AAAA HH:mm): ");
                    String fimStr = input.nextLine();
                    try {
                        LocalDateTime inicio = LocalDateTime.parse(inicioStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                        LocalDateTime fim = LocalDateTime.parse(fimStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                        facade.gerarAgendamentoManutencao(idRec, inicio, fim);
                    }
                    catch(Exception e) {
                        System.out.println("Formato de data/hora inválido.");
                    }
                    break;
                    
                case 0:
                	
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
