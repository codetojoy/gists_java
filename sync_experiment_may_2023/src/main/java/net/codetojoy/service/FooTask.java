
package net.codetojoy.service;

import java.net.http.*;
import java.net.URI;
import java.util.concurrent.*;
import java.util.function.Consumer;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.type.TypeReference;

import net.codetojoy.Timer;

class FooTask implements Callable<Foo> {
    private static final String JSON_BODY_FORMAT = "{\"name\": \"%s\", \"address\": \"%s\"}";
    private static final String TARGET_URL = "https://jsonplaceholder.typicode.com/posts";

    String name;
    String address;
    Consumer<Boolean> callback;

    public FooTask(String name, String address, Consumer<Boolean> callback) {
        this.name = name;
        this.address = address;
        this.callback = callback;
    }

    void emitLog(String msg) {
        long threadId = Thread.currentThread().getId();
        System.err.println("TRACER tid: " + threadId + " " + msg);
    }

    @Override
    public Foo call() throws Exception { 
        Foo result = null;
        boolean ok = true;
    
        try {
            Timer timer = new Timer();

            result = internalDoSomething(name, address);

            if (timer.exceedsThreshold()) {
                emitLog("WARN " + timer.getElapsed("Foo.doSomething"));
            }
        } catch (Exception ex) {
            ok = false;
            emitLog("ERROR caught ex: " + ex.getMessage());
        }

        if (callback != null) {
            callback.accept(ok);
        }

        return result;
    }

    protected Foo internalDoSomething(String name, String address) {
        Foo result = null; 

        try {
            pathogenicSimulatedDelay();

            URI targetURI = new URI(TARGET_URL);
            String jsonBody = String.format(JSON_BODY_FORMAT, name, address);
            HttpRequest httpRequest = HttpRequest.newBuilder()
                                                 .uri(targetURI)
                                                 .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                                                 .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.readValue(response.body(), Foo.class);
            emitLog("OK");
        } catch (Exception ex) {
            emitLog("ERROR caught ex: " + ex.getMessage());
        }

        return result;
    }

    private void pathogenicSimulatedDelay() {
        try {
            Thread.sleep(3 * 1000);
        } catch (Exception ex) {
        }
    }
}
