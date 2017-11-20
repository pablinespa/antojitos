package ucb.bo.edu.antojitosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import retrofit2.Response;

public class AboutActivity extends AppCompatActivity {

    private TextView description;
    private Realm antojitoRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        this.description = (TextView) findViewById(R.id.description_about);

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

        this.description.setText(about.getDescription());
    }

    public void atras(View v) {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_registeraction_register) {
            Toast.makeText(this, "Por favor contactese con nosotros para registrar su Restaurant. Gracias", Toast.LENGTH_LONG).show();
            return true;
        }

        if (id == R.id.action_about) {
            Intent i = new Intent(this, AboutActivity.class);
            startActivity(i);
        }
        if (id == R.id.action_terms) {
            Intent i = new Intent(this, TermActivity.class);
            startActivity(i);
        }
        if (id == R.id.action_contact) {
            Intent i = new Intent(this, ContactActivity.class);
            startActivity(i);
        }
        if (id == R.id.action_search) {
            Intent i = new Intent(this, SearchActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}