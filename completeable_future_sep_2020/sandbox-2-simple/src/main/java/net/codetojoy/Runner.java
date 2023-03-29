
package net.codetojoy;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

public class Runner {
    public static void main(String[] args) throws Exception {
        CompletableFuture<String> nameFuture = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("TRACER 'working' ...");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
            }
            return "Mozart";
        });

        CompletableFuture<String> greetingFuture = nameFuture.thenApply(name -> {
           return "Hello " + name;
        });

        System.out.println("TRACER : " + greetingFuture.get()); 
        System.out.println("Ready.");
    }
}
