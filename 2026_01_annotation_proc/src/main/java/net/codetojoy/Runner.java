package net.codetojoy;

public class Runner {

    public static void main(String[] args) {
        AnnotationProcessor processor = new AnnotationProcessor();
        processor.process(ExampleController.class);
    }
}
