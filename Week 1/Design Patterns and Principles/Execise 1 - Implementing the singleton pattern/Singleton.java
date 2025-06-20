import java.util.*;

public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {
        System.out.println("Singleton instance created!");
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from the Singleton instance!");
    }

    public void log(String message) {
        System.out.println("Singleton Log: " + message);
    }

    public static void main(String[] args) {

        Singleton singleton1 = Singleton.getInstance();
        singleton1.showMessage();
        singleton1.log("Accessed first time.");

        Singleton singleton2 = Singleton.getInstance();
        singleton2.showMessage();
        singleton2.log("Accessed second time.");

        if (singleton1 == singleton2) {
            System.out.println("\nBoth singleton1 and singleton2 refer to the same instance!");
        } else {
            System.out.println("\nSomething went wrong! Instances are different.");
        }

        System.out.println("\nHashcode of singleton1: " + singleton1.hashCode());
        System.out.println("Hashcode of singleton2: " + singleton2.hashCode());
    }
}


