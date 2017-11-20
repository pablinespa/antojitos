package ucb.bo.edu.antojitosapp;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Pablo on 11/9/2017.
 */

public class AboutDeserialized implements JsonDeserializer<AboutResponse> {

    private String about_id;

    public AboutDeserialized(String about_id) {
        this.about_id = about_id;
    }

    @Override
    public AboutResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        System.out.println("deserialize");
        System.out.println(new Gson().toJson(json.getAsJsonObject()));
        System.out.println("deserialize other");
        System.out.println( "deserialize other " + json.getAsJsonObject() );

        JsonObject jsonObject = json.getAsJsonObject();
        System.out.println( jsonObject.get(ConstantsRestApi.TERM_DESCRIPTION) );

        AboutResponse resp = new AboutResponse(jsonObject.get(ConstantsRestApi.TERM_DESCRIPTION).getAsString());
        return resp;
    }
}
