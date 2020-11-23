
import java.util.concurrent.*;

import org.apache.commons.lang3.tuple.*;

class Item {
    long millis;

    Item() { 
        millis = System.currentTimeMillis();
    }
}

class BooleanSupplier {
    Boolean followUpTask() {
        return (System.currentTimeMillis() % 2 == 0) ? true : false;
    }
}

class Server {
    Item getItem() { 
        return new Item();
    }

    ImmutablePair<CompletableFuture<Boolean>,Item> getCombo() throws Exception {
        Item item = getItem();

        BooleanSupplier booleanTask = new BooleanSupplier();
        CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> booleanTask.followUpTask());
       
        ImmutablePair<CompletableFuture<Boolean>,Item> result = new ImmutablePair<>(future, item);
        return result;
    }
}

class Client {
    void go() throws Exception {
        Server server = new Server();
        ImmutablePair<CompletableFuture<Boolean>,Item> pair = server.getCombo();
        Item item = pair.getRight();
        boolean value = pair.getLeft().get();
        System.out.println("TRACER item.millis: " + item.millis + " value: " + value);
    }
}

public class Combine {
    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.go();
        System.out.println("Ready.");
    }
}
