package ucb.bo.edu.antojitosapp;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by ASUS on 9/11/2017.
 */

public class TermDeserialized  implements JsonDeserializer<TermResponse> {

    private String term_id;

    public TermDeserialized(String term_id) {
        this.term_id = term_id;
    }

    @Override
    public TermResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        System.out.println("deserialize");
        System.out.println(new Gson().toJson(json.getAsJsonObject()));
        System.out.println("deserialize other");
        System.out.println( json.getAsJsonObject() );
        System.out.println( "deserialize other " +  json.getAsJsonObject() );

        JsonObject jsonObject = json.getAsJsonObject();
        System.out.println( jsonObject.get(ConstantsRestApi.TERM_DESCRIPTION) );

        TermResponse resp = new TermResponse(jsonObject.get(ConstantsRestApi.TERM_DESCRIPTION).getAsString());
        return resp;
    }

}
