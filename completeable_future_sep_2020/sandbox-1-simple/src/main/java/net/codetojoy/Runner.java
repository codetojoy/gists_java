
package net.codetojoy;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

public class Runner {
    void goSingle() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Task());

        String result = future.get();
        System.out.println(result);
    }

    List<String> goMany(List<Supplier<String>> tasks) throws Exception {
        List<CompletableFuture<String>> futures = new ArrayList<>();

        for (var task : tasks) {
            var f = CompletableFuture.supplyAsync(task);
            futures.add(f);
        }

        var futuresArray = futures.toArray(new CompletableFuture[futures.size()]);

        var allFutures = CompletableFuture.allOf(futuresArray);

        // CompletableFuture<List<String>>
        var compoundFuture = allFutures.thenApply(v -> {
            return futures.stream()
                             .map(f -> f.join())
                             .collect(Collectors.toList());
        });

        var results = compoundFuture.get();
        return results;
    }

    public static void main(String[] args) throws Exception {
        var runner = new Runner();

        int which = 2;

        if (which == 1) {
            runner.goSingle();
        } else if (which == 2) {
            var tasks = new ArrayList<Supplier<String>>();

            for (var i = 0; i < 5; i++) {
                tasks.add(new Task());
            }

            var results = runner.goMany(tasks);

            for (var result : results) {
                System.out.println(LogUtil.getLogPrefix() + " result: " + result);
            }
        }
        System.out.println("Ready.");
    }
}
