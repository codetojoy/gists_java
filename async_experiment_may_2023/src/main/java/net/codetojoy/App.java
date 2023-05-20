package net.codetojoy;

import java.util.*;
import java.util.concurrent.*;

import net.codetojoy.models.Account;
import net.codetojoy.service.*;
import net.codetojoy.utils.Logger;

public class App {
    private final FooService fooService = new FooService();

    private void callFooService() throws Exception {
        String name = "EVH";
        String address = "Pasadena";
        List<Account> accounts = List.of(new Account(name, address));
        CompletableFuture<Collection<Foo>> future = fooService.fetchInfoForAccounts(accounts);
        var foos = future.get();
        for (Foo foo : foos) {
            Logger.log("App received foo id: " + foo.getId() + " name: " + foo.getName());
        }
    }

    public static void main(String... args) throws Exception {
        App app = new App();

        app.callFooService();

        System.out.println("TRACER Ready.");
    }
}
