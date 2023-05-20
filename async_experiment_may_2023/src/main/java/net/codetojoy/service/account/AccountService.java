
package net.codetojoy.service.account;

import java.util.*;
import java.util.concurrent.*;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Stream;

import net.codetojoy.models.Account;
import net.codetojoy.utils.*;

public class AccountService {
    private static final String TARGET_URL_FORMAT = Constants.ACCOUNT_URL_FORMAT;
    private ExecutorService executor = Executors.newFixedThreadPool(5);

    public void shutdown() {
        executor.shutdown();
    }

    public CompletableFuture<Collection<Account>> fetchInfoForAccounts(List<Account> accounts) {
        final List<CompletableFuture<Collection<Account>>> futures =
            accounts.stream().map(account -> {
                URLFetcher fetcher = new URLFetcher();
                int id = account.getId();
                String name = account.getName();
                String address = account.getAddress();
                int delayInSeconds = new MyRandom().getRandomInclusive(1,3);
                String targetURL = String.format(TARGET_URL_FORMAT, id, name, address, delayInSeconds);
                return fetcher.fetch(executor, targetURL);
            }).collect(toList());

        return futures.stream()
                .reduce(combineApiCalls())
                .orElse(CompletableFuture.completedFuture(emptyList()));
    }

    // https://theboreddev.com/parallel-api-calls-with-completablefuture/

    protected BinaryOperator<CompletableFuture<Collection<Account>>> combineApiCalls() {
        return (c1, c2) -> c1
                .thenCombine(c2, (fooList1, fooList2) -> {
                    return Stream.concat(fooList1.stream(), fooList2.stream()).collect(toList());
                });
    }
}
