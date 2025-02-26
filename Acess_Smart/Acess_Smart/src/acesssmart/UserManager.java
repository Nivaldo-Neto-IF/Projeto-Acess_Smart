package acesssmart;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
public class UserManager {
    private Map<String, Usuario> usuarios = new HashMap<>();
    private int nextId = 1;
    
    public UserManager() {
        adicionarUsuario(new Usuario(nextId++, "Katyusho", "admin", "admin123", true));
        adicionarUsuario(new Usuario(nextId++, "Nivaldo ", "user", "user123", false));
        adicionarUsuario(new Usuario(nextId++, "Braulio ", "user2", "user1234", false));
        adicionarUsuario(new Usuario(nextId++, "Vinicius ", "user3", "user12345", false));
  }
    
    public boolean usuarioExiste(String login) {
        return usuarios.containsKey(login);
    }
    
    public void adicionarUsuario(Usuario usuario) {
        usuarios.put(usuario.getLogin(), usuario);
    }
    public List<Usuario> getAdministradores() {
    List<Usuario> admins = new ArrayList<>();
    for (Usuario u : usuarios.values()) {
         if (u.isAdmin()) {
             admins.add(u);
            }
        }
        return admins;
    }
    public Usuario autenticar(String login, String senha) {
        Usuario usuario = usuarios.get(login);
        if (usuario != null && usuario.autenticar(login, senha)) {
            return usuario;
        }
        return null;
    }
    
    public int getNextId() {
        return nextId++;
    }
    

    public Usuario getUsuarioById(int id) {
        for (Usuario usuario : usuarios.values()) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }
    
    public void listarUsuarios() {
        System.out.println("Lista de usuários:");
        for (Usuario usuario : usuarios.values()) {
            System.out.println("ID: " + usuario.getId() 
                    + " - Nome: " + usuario.getNome() 
                    + " - Admin: " + (usuario.isAdmin() ? "Sim" : "Não"));
        }
    }
}
