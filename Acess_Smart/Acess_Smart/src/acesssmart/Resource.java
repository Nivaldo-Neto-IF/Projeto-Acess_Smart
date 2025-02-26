//factory method
package acesssmart;

public abstract class Resource {
    public abstract String getDescricao();
}
  
class Rampa extends Resource {
    public String getDescricao() {
        return "Rampa da quadra";
    }
}
class Elevador extends Resource {
    public String getDescricao() {
        return "Elevador da biblioteca";
    }
}
class piso extends Resource {
    public String getDescricao() {
        return "Piso Tatil";
    }
}

class estacionamento extends Resource {
    public String getDescricao() {
        return "Vaga estacionamento";
    }
}

class sala extends Resource {
    public String getDescricao() {
        return "Sala Adaptada";
    }
}