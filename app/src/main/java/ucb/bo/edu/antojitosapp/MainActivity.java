package ucb.bo.edu.antojitosapp;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements
        OnMapReadyCallback {

    private Toolbar mToolBar;
    private Realm antojitoRealm;
    private GoogleMap map;

    final String TAG = "GPS";
    final String gpsLocationProvider = LocationManager.GPS_PROVIDER;
    final String networkLocationProvider = LocationManager.NETWORK_PROVIDER;
    String wantPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    public double latitude;
    public double longitude;

    Intent restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.antojitoRealm = Realm.getDefaultInstance();

        this.mToolBar = (Toolbar) findViewById(R.id.toolbar);

        if (this.mToolBar != null) {
            setSupportActionBar(this.mToolBar);
        }

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        restaurant = new Intent(this, RestaurantActivity.class);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Log.d(TAG, "locationManager " + locationManager);
        LocationManager locationManager_ = (LocationManager) getSystemService(Service.LOCATION_SERVICE);
        Log.d(TAG, "locationManager_ " + locationManager_);

        if (checkPermission(wantPermission)) {
            Location lastKnownLocationGps = locationManager.getLastKnownLocation(gpsLocationProvider);
            Location lastKnownLocationNetwork = locationManager.getLastKnownLocation(networkLocationProvider);

            if (lastKnownLocationGps == null) {
                Log.d(TAG, "NO GPS");

                if (lastKnownLocationNetwork == null) {
                    Log.d(TAG, "NO Network");
                    Log.d(TAG, "NO Location!");

                    showSettingsAlert();

                } else {
                    Log.d(TAG, "Network " + lastKnownLocationNetwork.toString());
                    Log.d(TAG, "Location (Network)" + lastKnownLocationNetwork.getLatitude() + ", " + lastKnownLocationNetwork.getLongitude());
                    latitude = lastKnownLocationNetwork.getLatitude();
                    longitude = lastKnownLocationNetwork.getLongitude();


                }
            } else {
                Log.d(TAG, "GPS " + lastKnownLocationGps.toString());

                if (lastKnownLocationNetwork == null) {
                    Log.d(TAG, "NO Network");
                    Log.d(TAG, "Location (GPS) " + lastKnownLocationGps.getLatitude() + ", " + lastKnownLocationGps.getLongitude());
                    Log.d(TAG, "Time (GPS) " + lastKnownLocationGps.getTime());
                    Log.d(TAG, "Accuracy (GPS) " + lastKnownLocationGps.getAccuracy());
                } else {
                    Log.d(TAG, "Network " + lastKnownLocationNetwork.toString());

                    //Both Location provider have last location decide location base on accuracy
                    if (lastKnownLocationGps.getAccuracy() <= lastKnownLocationNetwork.getAccuracy()) {
                        Log.d(TAG, "Location (GPS) " + lastKnownLocationGps.getLatitude() + ", " + lastKnownLocationGps.getLongitude());
                        latitude = lastKnownLocationGps.getLatitude();
                        longitude = lastKnownLocationGps.getLongitude();
                    } else {
                        Log.d(TAG, "Location (Network) " + lastKnownLocationNetwork.getLatitude() + ", " + lastKnownLocationNetwork.getLongitude());
                        latitude = lastKnownLocationNetwork.getLatitude();
                        longitude = lastKnownLocationNetwork.getLongitude();
                    }

                }
            }
        }

        /*String isbn = "0596154615";
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPointApi endPointApi = restApiAdapter.connexionApi(restApiAdapter.buildGsonDeserializedBook(isbn));
        Call<BookResponse> bookResponseCall = endPointApi.getList("data", "json","ISBN:"+isbn);
        bookResponseCall.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                BookResponse book = response.body();
                System.out.println( "BookResponse" );
                System.out.println( new Gson().toJson(book));
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                System.out.println("Ocurrio un error" + t.getLocalizedMessage());
            }
        });*/

        String restaurant_id = "2";
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPointApi endPointApi = restApiAdapter.connexionApi(restApiAdapter.buildGsonDeserializedRestaurant(restaurant_id));
        Call<RestaurantResponse> restaurantResponseCall = endPointApi.getList(restaurant_id);

        restaurantResponseCall.enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                RestaurantResponse restaurant = response.body();
                System.out.println( "RestaurantResponse" );
                System.out.println( new Gson().toJson(restaurant));
            }

            @Override
            public void onFailure(Call<RestaurantResponse> call, Throwable t) {
                System.out.println( "Error fdsfsdsd" );
                System.out.println("Ocurrio un error" + t.getLocalizedMessage());
            }
        });

    }

    private boolean checkPermission(String permission) {
        if (Build.VERSION.SDK_INT >= 23) {
            int result = ContextCompat.checkSelfPermission(MainActivity.this, permission);
            if (result == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("GPS is not Enabled!");
        alertDialog.setMessage("Do you want to turn on GPS?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
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


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        LatLng pos = new LatLng(latitude, longitude);

        // Add a marker in Sydney and move the camera

        map.moveCamera(CameraUpdateFactory.newLatLng(pos));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.d(TAG, "POS (GPS) " + pos);
            return;
        }
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);

        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        MarkerOptions markerend = new MarkerOptions()
                .position(pos)
                .title("Titulo")
                .snippet("Subtitulo");
        // Changing marker icon
        markerend.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_CYAN));

        // adding marker
        map.addMarker(markerend);

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {

            @Override
            public boolean onMarkerClick(Marker arg0) {
                if(arg0.getTitle().equals("Titulo")) // if marker source is clicked
                {
                    Toast.makeText(MainActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                    restaurant = new Intent(MainActivity.this, RestaurantActivity.class);
                    startActivity(restaurant);
                }

                return true;
            }

        });

    }

    public void onLocationChanged(Location location) {

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        Log.e("latitude", "latitude--" + latitude);
        Log.e("latitude", "inside latitude--" + latitude);

    }

}
