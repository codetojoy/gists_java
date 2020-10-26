
import com.google.gson.*;

import java.lang.reflect.Type;
import java.nio.file.*;
import java.nio.charset.*;

class Location {
    public double lat;
    public double lng;

    public Location(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }
}

class MyDeserializer implements JsonDeserializer<Location> {
    @Override
    public Location deserialize(JsonElement json, 
                                Type typeOfT, 
                                JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        JsonObject coords = jsonObject.get("coordinates").getAsJsonObject();
        double lat = coords.get("lat").getAsDouble();
        double lng = coords.get("lng").getAsDouble();

        return new Location(lat, lng);
    }
}

public class Example {
    public Location parseCoords(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        var deserializer = new MyDeserializer();
        gsonBuilder.registerTypeAdapter(Location.class, deserializer);
        Gson customGson = gsonBuilder.create();  
        Location pos = customGson.fromJson(json, Location.class);  
        return pos;
    }

    public static void main(String[] args) {
        try {
            String fileName = args[0];
            Path path = FileSystems.getDefault().getPath(fileName);
            String content = Files.readString(path, StandardCharsets.US_ASCII);

            Example example = new Example();
            Location location = example.parseCoords(content);
            System.out.println("TRACER lat: " + location.lat);
            System.out.println("TRACER lng: " + location.lng);
            System.out.println("Ready.");
        } catch (Exception ex) {
            System.err.println("caught exception: " + ex.getMessage());
        }
    }
}
