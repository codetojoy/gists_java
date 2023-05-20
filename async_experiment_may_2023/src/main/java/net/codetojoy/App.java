package net.codetojoy;

import java.util.*;
import java.util.concurrent.*;

import net.codetojoy.models.Account;
import net.codetojoy.service.*;
import net.codetojoy.utils.Logger;

public class App {
    private final FooService fooService = new FooService();

    private void callFooService() throws Exception {
        List<Account> accounts = new ArrayList<>();
        for (var i = 1; i <= 20; i++) {
            String name = "acct-" + (5150 + i);
            String address = i + " Longworth Ave";
            accounts.add(new Account(name, address));
        }

        CompletableFuture<Collection<Foo>> future = fooService.fetchInfoForAccounts(accounts);
        var foos = future.get();
        for (Foo foo : foos) {
            Logger.log("App received foo id: " + foo.getId() + " name: " + foo.getName());
        }

        fooService.shutdown();
    }

    public static void main(String... args) throws Exception {
        App app = new App();

        app.callFooService();

        System.out.println("TRACER Ready.");
    }
}
