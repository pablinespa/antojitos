package ucb.bo.edu.antojitosapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ucb.bo.edu.antojitosapp.ConstantsRestApi;
import ucb.bo.edu.antojitosapp.RestaurantResponse;

/**
 * Created by ASUS on 1/11/2017.
 */

public interface EndPointApi {

    @GET(ConstantsRestApi.URL_BOOK)
    Call<BookResponse> getList(@Query("jscmd") String jscmd, @Query("format") String format, @Query("bibkeys") String bibkeys );

    @GET(ConstantsRestApi.URL_RESTAURANT)
    Call<RestaurantResponse> getList(@Query("restaurant_id") String restaurant_id );

    @GET(ConstantsRestApi.URL_TERM)
    Call<TermResponse> getTerm(@Query("term_id") String term_id );

    @GET(ConstantsRestApi.URL_ABOUT)
    Call<AboutResponse> getAbout(@Query("about_id") String about_id);

    @GET(ConstantsRestApi.URL_ABOUT)
    Call<About> getAboutJsonObjectData();

    @GET(ConstantsRestApi.URL_TERM)
    Call<Term> getTermJsonObjectData();

    @POST(ConstantsRestApi.URL_RESTAURANT)
    @FormUrlEncoded
    Call<List<RestaurantResponse>> getRest(@Field("latitude") Double latitude,
                                           @Field("longitude") Double longitude);

    @POST(ConstantsRestApi.URL_RESTAURANT)
    Call<List<RestaurantResponse>> getRestTwo();
}
