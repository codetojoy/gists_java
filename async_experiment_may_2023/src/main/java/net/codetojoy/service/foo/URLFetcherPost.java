
package net.codetojoy.service.foo;

import java.net.http.*;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.type.TypeReference;

import net.codetojoy.utils.*;

public class URLFetcherPost {
    private static final String JSON_BODY_FORMAT = "{\"name\": \"%s\", \"address\": \"%s\"}";

    public CompletableFuture<Collection<Foo>> fetch(Executor executor, String url, String name, String address) {
        return CompletableFuture.supplyAsync(() -> {
            Logger.log("begin fetching URL");
            Foo result = null;

            try {
                Timer timer = new Timer();

                result = makeRequest(url, name, address);

                Logger.log("INFO " + timer.getElapsed("URLFetcher fetch"));
            } catch (Exception ex) {
                Logger.log("ERROR caught ex: " + ex.getMessage());
            }

            return List.of(result);
        }, executor);
    }

    protected Foo makeRequest(String url, String name, String address) {
        Foo result = null;

        try {
            pathogenicSimulatedDelay();

            URI targetURI = new URI(url);
            String jsonBody = String.format(JSON_BODY_FORMAT, name, address);
            HttpRequest httpRequest = HttpRequest.newBuilder()
                                                 .uri(targetURI)
                                                 .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                                                 .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.readValue(response.body(), Foo.class);
            result.setName(name);
            Logger.log("URLFetcher OK");
        } catch (Exception ex) {
            Logger.log("URLFetcher ERROR caught ex: " + ex.getMessage());
        }

        return result;
    }

    private int getRandom(int min, int max) {
        Random random = new Random();
        int x = (max - min) + 1;
        // result is min + [0,max-min]
        int result =  min + random.nextInt(x);
        return result;
    }

    private void pathogenicSimulatedDelay() {
        try {
            int delayInSeconds = getRandom(1,3);
            Thread.sleep(delayInSeconds * 1000);
        } catch (Exception ex) {
        }
    }
}
