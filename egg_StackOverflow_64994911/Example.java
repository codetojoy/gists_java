
class CloseableResource implements AutoCloseable {
    @Override
    public void close() {
        System.out.println("TRACER CR close");
    }
}

class Foo implements AutoCloseable {
    CloseableResource resource = new CloseableResource();

    @Override
    public void close() {
        System.out.println("TRACER Foo close");
        resource.close();
    }
}

class Bar implements AutoCloseable {
    Foo foo = new Foo();

    void bar() {} 

    @Override
    public void close() {
        System.out.println("TRACER Bar close");
        foo.close();
    }
}

public class Example {
    public static void main(String[] args) {
        try (Bar b = new Bar()) {
            b.bar();
        }
    }
}
