package ucb.bo.edu.antojitosapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ASUS on 1/11/2017.
 */

public class RestApiAdapter {

    /**
     * Get connexion rest api generic with param gson converter target objet
     *
     * @param gson
     * @return EndPointApi
     */
    public EndPointApi connexionApi(Gson gson) {


        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantRestApi.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndPointApi.class);*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantsRestApi.URL_ANTOJITOS)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndPointApi.class);
    }

    public Gson buildGsonDeserializedBook(String isbn) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(BookResponse.class, new BookDeserialized(isbn));
        return gsonBuilder.create();
    }

    public Gson buildGsonDeserializedRestaurant(String restaurant_id) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(RestaurantResponse.class, new RestaurantDeserialized(restaurant_id));
        return gsonBuilder.create();
    }

    public Gson buildGsonDeserializedTerm(String term_id) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(TermResponse.class, new TermDeserialized(term_id));
        return gsonBuilder.create();
    }

    public Gson buildGsonDeserializedAbout(String about_id) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(AboutResponse.class, new AboutDeserialized(about_id));
        return gsonBuilder.create();
    }


}
