
public class App {
    private void callFooService() {
        boolean check1 = true;
        boolean check2 = true;
        boolean check3 = true;

        if (check1) {
            System.out.println("TRACER check 1");
        } else if (check2) {
            System.out.println("TRACER check 2");
        } else if (check3) {
            System.out.println("TRACER check 3");
        } else {
            System.out.println("TRACER check else");
        }
    }

    public static void main(String... args) {
        App app = new App();

        app.callFooService();

        System.out.println("TRACER Ready.");
    }
}
