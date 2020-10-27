
public class Runner {
    public static final String OPERATION = "my-operation";
 
    public void operation() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new CustomPermission(OPERATION));
        }
        System.out.println("Operation is executed");
    }

    public static void main(String[] args) {
        // System.out.println("TRACER user.home: " + System.getProperty("user.home"));
        Runner runner = new Runner();
        runner.operation();
        System.out.println("Ready.");
    }
}
