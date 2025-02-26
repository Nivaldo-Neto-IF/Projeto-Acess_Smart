package acesssmart;

import java.util.*;

public class Notificationsi {
    private static Map<Integer, Observer> observers = new HashMap<>();
    private static Map<Integer, List<String>> pendingNotifications = new HashMap<>();

    public static void registerObserver(Usuario usuario) {
        observers.put(usuario.getId(), usuario);
        if (pendingNotifications.containsKey(usuario.getId())) {
            List<String> mensagens = pendingNotifications.get(usuario.getId());
            for (String mensagem : mensagens) {
                usuario.atualizar(mensagem);
            }
            pendingNotifications.remove(usuario.getId());
        }
    }

    public static void notifyUser(int userId, String message) {
        Observer observer = observers.get(userId);
        if (observer != null) {
            observer.atualizar(message);
        } 
        else {
            List<String> mensagens = pendingNotifications.getOrDefault(userId, new ArrayList<>());
            mensagens.add(message);
            pendingNotifications.put(userId, mensagens);
        }
    }

    public static void removeObserver(int userId) {
        observers.remove(userId);
        pendingNotifications.remove(userId);
    }
}