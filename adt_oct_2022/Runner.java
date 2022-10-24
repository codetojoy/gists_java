
sealed interface ADT {
    record Person(String eats) implements ADT {}
    record Robot(String chargesWith) implements ADT {}
}

public class Runner {
    public void nourishes(ADT x) {
        switch (x) {
            case ADT.Person(String eats) -> System.out.println("TRACER person eats: " + eats);
            case ADT.Robot(String chargesWith) -> System.out.println("TRACER robot charging: " + chargesWith);
        }
    }

    public static void main(String [] args) {
        try {
            var runner = new Runner();
            runner.nourishes(new ADT.Person("2 apples"));
            runner.nourishes(new ADT.Robot("5 V"));
            System.out.println("TRACER ready.");
        } catch (Exception ex) {
            System.err.println("TRACER caught ex: " + ex.getMessage());
        }
    }
}
