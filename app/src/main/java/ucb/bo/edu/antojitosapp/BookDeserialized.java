package ucb.bo.edu.antojitosapp;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Pablo on 11/8/2017.
 */

public class BookDeserialized implements JsonDeserializer<BookResponse> {

    private String isbn;

    public BookDeserialized(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public BookResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        System.out.println("deserialize");
        System.out.println(new Gson().toJson(json.getAsJsonObject()));

        JsonObject jsonObject = json.getAsJsonObject().get("ISBN:"+this.isbn).getAsJsonObject();
        JsonArray listaAuthor = jsonObject.get(ConstantsRestApi.BOOK_AUTHORS).getAsJsonArray();

        System.out.println("listaAuthor");
        System.out.println(listaAuthor);
        System.out.println(listaAuthor.size());

        for (int i = 0; i < listaAuthor.size(); i++) {
            JsonObject book_ = (JsonObject) listaAuthor.get(i);
            System.out.println("book_");
            System.out.println(book_);
            System.out.println(book_.get("name"));
            // Do whatever you have to do to myJsonObject...
        }


        BookResponse resp = new BookResponse(this.isbn,jsonObject.get(ConstantsRestApi.BOOK_TITLE).getAsString(), "Autor" ,"2017", 150);
        return resp;
    }

}
