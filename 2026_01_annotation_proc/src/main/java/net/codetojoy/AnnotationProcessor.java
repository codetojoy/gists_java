package net.codetojoy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationProcessor {

    public void process(Class<?> clazz) {
        System.out.println("Processing class: " + clazz.getName());
        System.out.println();

        processClassAnnotations(clazz);
        processMethodAnnotations(clazz);
    }

    private void processClassAnnotations(Class<?> clazz) {
        Annotation[] classAnnotations = clazz.getAnnotations();

        System.out.println("Class-level annotations:");
        if (classAnnotations.length == 0) {
            System.out.println("  (none)");
        } else {
            for (Annotation annotation : classAnnotations) {
                System.out.println("  @" + annotation.annotationType().getSimpleName());
            }
        }
        System.out.println();
    }

    private void processMethodAnnotations(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();

        System.out.println("Method-level annotations:");
        for (Method method : methods) {
            Annotation[] methodAnnotations = method.getAnnotations();
            if (methodAnnotations.length > 0) {
                System.out.println("  " + method.getName() + "():");
                for (Annotation annotation : methodAnnotations) {
                    System.out.println("    @" + annotation.annotationType().getSimpleName());
                }
            }
        }
    }
}
