
package net.codetojoy.service.account;

import java.net.http.*;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.type.TypeReference;

import net.codetojoy.models.*;
import net.codetojoy.utils.*;

public class URLFetcher {
    public CompletableFuture<Collection<Account>> fetch(Executor executor, String url) {
        return CompletableFuture.supplyAsync(() -> {
            Logger.log("begin fetching URL");
            Account result = null;

            try {
                Timer timer = new Timer();

                result = makeRequest(url);

                Logger.log("INFO " + timer.getElapsed("URLFetcher fetch"));
            } catch (Exception ex) {
                Logger.log("ERROR caught ex: " + ex.getMessage());
            }

            return List.of(result);
        }, executor);
    }

    protected Account makeRequest(String url) {
        Account result = null;

        try {
            URI targetURI = new URI(url);
            HttpRequest httpRequest = HttpRequest.newBuilder()
                                                 .uri(targetURI)
                                                 .GET()
                                                 .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.readValue(response.body(), Account.class);
            Logger.log("URLFetcher OK");
        } catch (Exception ex) {
            Logger.log("URLFetcher ERROR caught ex: " + ex.getMessage());
        }

        return result;
    }
}
