package ucb.bo.edu.antojitosapp;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by ASUS on 1/11/2017.
 */

public class RestaurantDeserialized implements JsonDeserializer<RestaurantResponse> {

    private String restaurant_id;

    public RestaurantDeserialized(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    @Override
    public RestaurantResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        System.out.println("deserialize");
        System.out.println(new Gson().toJson(json.getAsJsonObject()));

        RestaurantResponse resp = new RestaurantResponse(this.restaurant_id);
        return resp;
    }
}
