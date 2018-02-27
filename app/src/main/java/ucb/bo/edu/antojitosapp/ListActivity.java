package ucb.bo.edu.antojitosapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity {

    ArrayList<String> restNames = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantsRestApi.URL_ANTOJITOS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EndPointApi service = retrofit.create(EndPointApi.class);

        Call<List<RestaurantResponse>> call = service.getRestTwo();

        call.enqueue(new Callback<List<RestaurantResponse>>() {
            @Override
            public void onResponse(Call<List<RestaurantResponse>> call, Response<List<RestaurantResponse>> response) {
                Log.e(" mainAction", "  list "+ response.body().toString());
                Log.e(" mainAction", "  list raw "+ response.raw());
                Log.e(" mainAction", "  list raw "+ new Gson().toJson(response.body()));

                System.out.println(" jsonArray");

                List<RestaurantResponse> res = response.body();

                System.out.println(res);
                System.out.println(res.size());

                for (RestaurantResponse object: res) {
                    System.out.println(" for 1");
                    System.out.println(object.getAddress());
                    System.out.println( Double.valueOf(object.getLatitude()).doubleValue() );
                    System.out.println(" for 1");

                    restNames.add(object.getName());
                }

                CustomAdapter customAdapter = new CustomAdapter(ListActivity.this, restNames);
                recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
            }

            @Override
            public void onFailure(Call<List<RestaurantResponse>> call, Throwable t) {
                Log.e("MainActivity ", "  error "+ t.toString());

            }
        });

        onList();
    }

    public void onList(){



    }
}
