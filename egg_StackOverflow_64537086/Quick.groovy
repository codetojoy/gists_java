
@Grab('com.google.code.gson:gson:2.8.6')

import com.google.gson.*;
import java.lang.reflect.Type;

class Location {

    public double lat;
    public double lng;

    public Location(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }
}

def parseCoords(String coords) {
    GsonBuilder gsonBuilder = new GsonBuilder();

JsonDeserializer<Location> deserializer = new JsonDeserializer<Location>() {  
    @Override
    public Location deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        double lat = jsonObject.get("coordinates").get("lat").getAsDouble();
        double lng = jsonObject.get("coordinates").get("lng").getAsDouble();

        return new Location(lat, lng);
    }
};
    gsonBuilder.registerTypeAdapter(Location.class, deserializer);

    Gson customGson = gsonBuilder.create();  
    Location pos = customGson.fromJson(coords, Location.class);  

    // Location pos = new Gson().fromJson(coords, Location.class);
    return pos; 
}

// --- main

def str = new File("data.json").getText()
def location = parseCoords(str)


println "TRACER lat: ${location.lat} lng: ${location.lng}"

println "Ready."
