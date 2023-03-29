
* link: https://www.callicoder.com/java-8-completablefuture-tutorial/
* CompletableFuture is extension to Future
    - also implements CompletableStage
* Future limitations:
    - can't be completed manually
    - `.get()` is blocking and too simple
    - can't chain Futures 
    - functional combinations
    - exception handling
* CompletableFuture addresses the issues above
    - `complete()` will manually complete
    - `runAsync()` takes Runnable
    - `supplyAsync()` takes Supplier<T> and returns T
    - can use lambdas as well
* CF uses ForkJoinPool but we can pass in our own thread-pool 

* `thenApply()`
* `thenAccept()` : take arg but return Void
* `thenRun()` : no arg, return Void

