package it.biasutti.mexj;

public class console {
    public static void log(String context, Exception e) {
        System.out.println("Errore in: " + context + ": " + e);
    }
    public static void log(String message) {
        System.out.println( message);
    }
    public static void log(String message, Object... args) {
        System.out.printf(message+"\n", args);
    }
}
