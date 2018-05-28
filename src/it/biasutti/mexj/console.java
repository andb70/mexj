package it.biasutti.mexj;

import java.util.Date;

public class console <T>{
    public static void log() {
        System.out.println();
    }
    public static void log(Date d) {
        System.out.println(d);
    }
    public static void log(String context, Exception e) {
        System.out.println("Errore in: " + context + ": " + e);
    }
    public static void log(String message) {
        System.out.println( message);
    }
    public static void log(String message, Object... args) {
        System.out.printf(message+"\n", args);
    }
    public static <T> IUser user(T user) {
        return (IUser)user;
    }
}
