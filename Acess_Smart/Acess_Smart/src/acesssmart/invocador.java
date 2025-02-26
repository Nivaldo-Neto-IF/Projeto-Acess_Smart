package acesssmart;
import java.util.ArrayList;
import java.util.List;


class Invocador {
    private List<Command> filaComandos = new ArrayList<>();

    public void adicionarComando(Command comando) {
        filaComandos.add(comando);
    }

    public void executarComandos() {
        for (Command comando : filaComandos) {
            comando.executar();
        }
        filaComandos.clear();
    }
}