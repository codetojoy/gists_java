package net.codetojoy;

public class App {
    public static void main(String... args) {
        App app = new App();

        int value = 6;
        Status status = new Status(value);
        String result = status.toString();
        System.out.println("TRACER result: " + result);

        System.out.println("TRACER Ready.");
    }
}
