
package net.codetojoy.service;

import java.net.http.*;
import java.net.URI;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.type.TypeReference;

import net.codetojoy.Timer;

public class FooService {
    private static final String JSON_BODY_FORMAT = "{\"name\": \"%s\", \"address\": \"%s\"}";
    private static final String TARGET_URL = "https://jsonplaceholder.typicode.com/posts";

    public Foo doSomething(String name, String address) {
        Foo result = null;

        try {
            Timer timer = new Timer();
            
            result = internalDoSomething(name, address);

            if (timer.exceedsThreshold()) {
                System.err.println("TRACER WARN " + timer.getElapsed("Foo.doSomething"));
            }
        } catch (Exception ex) {
            System.err.println("TRACER caught ex: " + ex.getMessage());
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
            System.out.println("TRACER OK");
        } catch (Exception ex) {
            System.err.println("TRACER caught ex: " + ex.getMessage());
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
