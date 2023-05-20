
package net.codetojoy.service;

import java.util.*;
import java.util.concurrent.*;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Stream;

import net.codetojoy.models.Account;
import net.codetojoy.utils.*;

public class FooService {
    private static final String TARGET_URL = "https://jsonplaceholder.typicode.com/posts";
    // private Executor executor = CompletableFuture.delayedExecutor(100, TimeUnit.MILLISECONDS);
    private ExecutorService executor = Executors.newFixedThreadPool(4);

    public void shutdown() {
        executor.shutdown();
    }

    public CompletableFuture<Collection<Foo>> fetchInfoForAccounts(List<Account> accounts) {
        final List<CompletableFuture<Collection<Foo>>> futures =
            accounts.stream().map(account -> {
                URLFetcher fetcher = new URLFetcher();
                String name = account.getName();
                String address = account.getAddress();
                return fetcher.fetch(executor, TARGET_URL, name, address);
            }).collect(toList());

        return futures.stream()
                .reduce(combineApiCalls())
                .orElse(CompletableFuture.completedFuture(emptyList()));
    }

    // https://theboreddev.com/parallel-api-calls-with-completablefuture/

    private BinaryOperator<CompletableFuture<Collection<Foo>>> combineApiCalls() {
        return (c1, c2) -> c1
                .thenCombine(c2, (fooList1, fooList2) -> {
                    return Stream.concat(fooList1.stream(), fooList2.stream()).collect(toList());
                });
    }

}
