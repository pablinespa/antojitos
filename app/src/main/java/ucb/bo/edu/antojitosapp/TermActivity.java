package ucb.bo.edu.antojitosapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TermActivity extends AppCompatActivity {

    private TextView description;
    //private Realm antojitoRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);

        /*this.description = (TextView) findViewById(R.id.description_term);

        this.antojitoRealm = Realm.getDefaultInstance();

        String term_id = "2";

        RestApiAdapter termApiAdapter = new RestApiAdapter();
        EndPointApi endPointApiTerm = termApiAdapter.connexionApi(termApiAdapter.buildGsonDeserializedTerm(term_id));
        Call<TermResponse> termResponseCall = endPointApiTerm.getTerm(term_id);

        termResponseCall.enqueue(new retrofit2.Callback<TermResponse>() {
            @Override
            public void onResponse(Call<TermResponse> call, Response<TermResponse> response) {
                final TermResponse termResponse = response.body();
                System.out.println( "TermResponse" );
                System.out.println( termResponse );
                System.out.println( new Gson().toJson(termResponse));
                System.out.println( "antojitoRealm " + termResponse.getDescription());

                Realm realm = Realm.getDefaultInstance();

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        Term term = realm.where(Term.class).equalTo("description", termResponse.getDescription()).findFirst();

                        System.out.println( "antojitoRealm " );
                        System.out.println( "antojitoRealm " + term );

                        if(term==null){

                            Term term_ = realm.createObject(Term.class, UUID.randomUUID().toString());
                            term_.setDescription(termResponse.getDescription());
                        }else{
                            Term term_ = realm.createObject(Term.class, UUID.randomUUID().toString());
                            term_.setDescription(termResponse.getDescription());
                        }
                    }
                });

            }

            @Override
            public void onFailure(Call<TermResponse> call, Throwable t) {
                System.out.println( "Error Term" );
                System.out.println("Ocurrio un error" + t.getLocalizedMessage());
            }
        });

        Term term = this.antojitoRealm.where(Term.class)
                .findAll()
                .last(null);

        this.description.setText(term.getDescription());*/

        this.description = (TextView) findViewById(R.id.description_term);

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

        Call<Term> call = service.getTermJsonObjectData();

        call.enqueue(new Callback<Term>() {
            @Override
            public void onResponse(Call<Term> call, Response<Term> response) {
                Log.e(" mainAction", "  response "+ response.body().toString());
                Log.e(" mainAction", "  description - "+ response.body().getDescription().toString());

                description.setText(response.body().getDescription().toString());
            }

            @Override
            public void onFailure(Call<Term> call, Throwable t) {
                Log.e("MainActivity ", "  error "+ t.toString());

            }
        });
    }

}
