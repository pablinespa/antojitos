package ucb.bo.edu.antojitosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AboutActivity extends AppCompatActivity {

    private TextView description;
    //private Realm antojitoRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        /*this.description = (TextView) findViewById(R.id.description_about);

        this.antojitoRealm = Realm.getDefaultInstance();

        String about_id = "2";

        RestApiAdapter termApiAdapter = new RestApiAdapter();
        EndPointApi endPointApiTerm = termApiAdapter.connexionApi(termApiAdapter.buildGsonDeserializedAbout(about_id));
        Call<AboutResponse> aboutResponseCall = endPointApiTerm.getAbout(about_id);

        aboutResponseCall.enqueue(new retrofit2.Callback<AboutResponse>() {
            @Override
            public void onResponse(Call<AboutResponse> call, Response<AboutResponse> response) {
                final AboutResponse aboutResponse = response.body();
                System.out.println( "AboutResponse" );
                System.out.println( aboutResponse );
                System.out.println( new Gson().toJson(aboutResponse));
                System.out.println( "antojitoRealm " + aboutResponse.getDescription());

                Realm realm = Realm.getDefaultInstance();

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        About about = realm.where(About.class).equalTo("description", aboutResponse.getDescription()).findFirst();

                        System.out.println( "antojitoRealm " );
                        System.out.println( "antojitoRealm About " + about );

                        if(about==null){

                            About about_ = realm.createObject(About.class, UUID.randomUUID().toString());
                            about_.setDescription(aboutResponse.getDescription());
                        }else{
                            About about_ = realm.createObject(About.class, UUID.randomUUID().toString());
                            about_.setDescription(aboutResponse.getDescription());
                        }
                    }
                });

            }

            @Override
            public void onFailure(Call<AboutResponse> call, Throwable t) {
                System.out.println( "Error Tern" );
                System.out.println("Ocurrio un error" + t.getLocalizedMessage());
            }
        });

        About about = this.antojitoRealm.where(About.class)
                .findAll()
                .last(null);

        this.description.setText(about.getDescription());*/

        this.description = (TextView) findViewById(R.id.description_about);

        getRetrofitObject();

    }

    public void atras(View v) {
        finish();
    }

    public void getRetrofitObject(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantsRestApi.URL_ANTOJITOS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EndPointApi service = retrofit.create(EndPointApi.class);

        Call<About> call = service.getAboutJsonObjectData();

        call.enqueue(new Callback<About>() {
            @Override
            public void onResponse(Call<About> call, Response<About> response) {
                Log.e(" mainAction", "  response "+ response.body().toString());
                Log.e(" mainAction", "  description - "+ response.body().getDescription().toString());

                description.setText(response.body().getDescription().toString());
            }

            @Override
            public void onFailure(Call<About> call, Throwable t) {
                Log.e("MainActivity ", "  error "+ t.toString());

            }
        });
    }

}