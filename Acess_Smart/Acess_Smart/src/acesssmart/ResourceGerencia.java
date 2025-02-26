package acesssmart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceGerencia {
    private List<Resource> recursos;
    private Map<Integer, String> avisosManutencao;

    public ResourceGerencia() {
        recursos = new ArrayList<>();
        avisosManutencao = new HashMap<>();
        recursos.add(ResourceFactory.criarRecurso("estacionamento")); 
        recursos.add(ResourceFactory.criarRecurso("sala"));
    }
    
    public void adicionarRecurso(String tipo) {
        Resource novoRecurso = ResourceFactory.criarRecurso(tipo);
        recursos.add(novoRecurso);
        System.out.println("Recurso adicionado: " + novoRecurso.getDescricao());
    }
    
    public void consultarRecursos() {
        System.out.println("Recursos disponíveis:");
        for (int i = 0; i < recursos.size(); i++) {
            int id = i + 1;
            Resource r = recursos.get(i);
            String descricao = r.getDescricao();
            String aviso = avisosManutencao.get(id);
            if (aviso != null) {
                descricao += " (Em Manutenção " + aviso + ")";
            }
            System.out.println(id + ". " + descricao);
        }
    }
    
    public String getRecursoDescricao(int recursoId) {
        if (recursoId > 0 && recursoId <= recursos.size()) {
            Resource r = recursos.get(recursoId - 1);
            String descricao = r.getDescricao();
            String aviso = avisosManutencao.get(recursoId);
            if (aviso != null) {
                descricao += " (!!!Em Manutenção!!! " + aviso + ")";
            }
            return descricao;
        }
        return "Recurso desconhecido";
    }
    
    public void setAvisoManutencao(int recursoId, String aviso) {
        avisosManutencao.put(recursoId, aviso);
        System.out.println("Aviso de manutenção definido para o recurso ID " + recursoId + ": " + aviso);
    }
    
    public void removeAvisoManutencao(int recursoId) {
        avisosManutencao.remove(recursoId);
        System.out.println("Aviso de manutenção removido para o recurso ID " + recursoId);
    }
    
    public String getAvisoManutencao(int recursoId) {
        return avisosManutencao.get(recursoId);
    }
}
