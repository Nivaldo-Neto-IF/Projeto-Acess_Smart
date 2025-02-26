package acesssmart;

public class ResourceFactory {
    public static Resource criarRecurso(String tipo) {
        switch(tipo.toLowerCase()) {
            case "rampa":
                return new Rampa();
                
            case "elevador":
                return new Elevador();
                
            case "sala":
                return new sala();
                
            case "estacionamento":
                return new estacionamento();
            default:
                System.out.println("Tipo de recurso não reconhecido: \"" + tipo 
                        + "\". Cadastrando como recurso gerador.");
                return new RecursoGerador(tipo);
        }
    }
}