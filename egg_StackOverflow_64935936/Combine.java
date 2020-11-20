
import java.util.function.*;
import java.util.concurrent.*;

class Item {
    long millis;

    Item() { 
        millis = System.currentTimeMillis();
    }
}

class BooleanSupplier implements Supplier<Boolean> {
    @Override
    public Boolean get() { 
        return followUpTask();
    }

    Boolean followUpTask() {
        return (System.currentTimeMillis() % 2 == 0) ? true : false;
    }
}

class Server {
    Item getItem() { 
        return new Item();
    }

    void getCombo(BiFunction<Boolean,Item,Void> handler) throws Exception {
        CompletionStage<Item> itemStage = CompletableFuture.completedFuture(getItem()); 

        Supplier<Boolean> booleanTask = new BooleanSupplier();
        CompletionStage<Boolean> booleanStage = CompletableFuture.supplyAsync(booleanTask);
       
        CompletionStage<Void> comboStage = booleanStage.thenCombineAsync(itemStage, handler);
        ((CompletableFuture) comboStage).join();
    }
}

class Client implements BiFunction<Boolean,Item,Void> {
    Item item;
    boolean value;

    @Override
    public Void apply(Boolean value, Item item) {
        this.item = item;
        this.value = value;
        return null;
    }

    void go() throws Exception {
        Server server = new Server();
        server.getCombo(this);
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
