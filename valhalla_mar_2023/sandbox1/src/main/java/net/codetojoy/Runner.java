
package net.codetojoy;

public class Runner {
    public static void main(String[] args) {
        try {
            var factors = new Factors(33);
            System.out.println("TRACER " + factors.toString());

            // ---------
            System.out.println("TRACER hello from Runner");
            System.out.println("Ready.");
        } catch (Exception ex) {
            System.err.println("caught exception: " + ex.getMessage());
        }
    }
}
