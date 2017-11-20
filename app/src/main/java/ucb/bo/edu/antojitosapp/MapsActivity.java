package ucb.bo.edu.antojitosapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-17.398853, -66.224407);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        MarkerOptions markerend = new MarkerOptions().position(
                sydney).title("Titulo").snippet("Subtitulo");

        // Changing marker icon
        markerend.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_CYAN));

        // adding marker
        mMap.addMarker(markerend);

        // Add lines
        mMap.addPolyline(new PolylineOptions().geodesic(true)
                .add(sydney)  // Sydney
                .add(new LatLng(-33.919953, 151.230328))  // Fiji
                .add(new LatLng(-33.920578, 151.230214))  // Hawaii

        );

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(sydney).zoom(16).build();
        mMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));


    }
}
