package net.codetojoy;

import java.util.*;
import java.util.concurrent.*;

import net.codetojoy.models.Account;
import net.codetojoy.service.foo.*;
import net.codetojoy.service.account.*;
import net.codetojoy.utils.Logger;
import net.codetojoy.utils.Timer;

public class App {
    private final FooService fooService = new FooService();
    private final AccountService accountService = new AccountService();

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

    private void callAccountService() throws Exception {
        List<Account> accounts = new ArrayList<>();
        int numAccounts = 50;
        for (var i = 1; i <= numAccounts; i++) {
            int id = i * i;
            String name = "acct-" + (5150 + i);
            String address = i + "_Longworth_Ave";
            accounts.add(new Account(id, name, address));
        }

        var timer = new net.codetojoy.utils.Timer();
        CompletableFuture<Collection<Account>> future = accountService.fetchInfoForAccounts(accounts);
        Collection<Account> receivedAccounts = future.get();
        Logger.log(timer.getElapsed("App overall request time"));

        for (Account account : receivedAccounts) {
            Logger.log("App received account: " + account.toString());
        }

        accountService.shutdown();
    }

    public static void main(String... args) throws Exception {
        App app = new App();

        // app.callFooService();
        app.callAccountService();

        System.out.println("TRACER Ready.");
    }
}
