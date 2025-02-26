package acesssmart;

public class ObserverManager {
    private static ResourceNotifier notifier = new ResourceNotifier();
    
    public static ResourceNotifier getNotifier() {
        return notifier;
    }
}