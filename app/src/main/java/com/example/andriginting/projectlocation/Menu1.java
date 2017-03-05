package com.example.andriginting.projectlocation;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.andriginting.projectlocation.R.array.latitude;
import static com.example.andriginting.projectlocation.R.array.longitude;

public class Menu1 extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap mGoogleMap;
    View mView;
    ImageView lainnya, apotek, lainnya2, lainnya3;
    String[] nama_tempat;
    private Marker marker;
    final static String API_KEY="AIzaSyDmp_XKCnsqY_dPnGq5OEPP5FZH5HvamD8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu1);

        nama_tempat = getResources().getStringArray(R.array.nama_tempat);

        //cek koneksi google server
        if (GoogleServicesAvailable()) {
            initmap();
            Toast.makeText(Menu1.this, "Terhubung...", Toast.LENGTH_SHORT).show();
        } else {
            //gagal tampilkan maps
        }

        lainnya = (ImageView) findViewById(R.id.lainnya);
        lainnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu1.this, Menu_kategori.class));
            }
        });

        lainnya2 = (ImageView) findViewById(R.id.lainnya2);
        lainnya2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu1.this, Menu_kategori.class));
            }
        });

        lainnya3 = (ImageView) findViewById(R.id.lainnya3);
        lainnya3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu1.this, Menu_kategori.class));
            }
        });
        apotek = (ImageView) findViewById(R.id.apotek);
        apotek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apotek();
            }
        });
    }

    public void apotek(){
        double lat = -7.94203712;
        double lng = 112.60936975;
        Bundle bundle = new Bundle();
        Intent intent = new Intent(Menu1.this, menu_cari.class);
        bundle.putDouble(menu_cari.TAG1,lat);
        bundle.putDouble(menu_cari.TAG2, lng);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //tambah icon ke actionbar
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                startActivity(new Intent(Menu1.this, profil.class));
                return true;
            case R.id.action_search:
                startActivity(new Intent(Menu1.this, menu_cari.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void initmap() {

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.MapFragment);
        mapFragment.getMapAsync(this);
    }

    public boolean GoogleServicesAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(Menu1.this, "Koneksi Gagal ", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getApplicationContext());
        mGoogleMap = googleMap;
        //get current location
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return ;
//        }mGoogleMap.setMyLocationEnabled(true);
        setUpMap();
       //foodNearby();

    }

    private void setUpMap() {
        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(-7.9524401, 112.6129181)).title("Universitas Brawijaya").snippet("UB"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(-7.9460112, 112.61243552)).title("Sambel Mak Tin"));
        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(-7.94696221, 112.6087904)).title("Sardo Swalayan"));
        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(-7.94203712, 112.60936975)).title("Apotek Kimia Farma"));
        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(-7.94807792, 112.61130095)).title("Ayam Bakar mas WW"));

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-7.9524401, 112.6129181), 15));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-7.9460112, 112.61243552), 15));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-7.94696221, 112.6087904), 15));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-7.94203712, 112.60936975), 15));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-7.94807792, 112.61130095), 15));
    }

    private void foodNearby() {
        String makanan = "hospital";
        mGoogleMap.clear();
        String url = getUrl(latitude, longitude, makanan);
        Object[] DataTransfer = new Object[2];
        DataTransfer[0] = mGoogleMap;
        DataTransfer[1] = url;
        Log.d("onClick", url);
        googleNearbyData getNearbyPlacesData = new googleNearbyData();
        getNearbyPlacesData.execute(DataTransfer);
    }

    public String getUrl(double latitude, double longitude, String nearbyPlace) {
        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=1000");
        googlePlacesUrl.append("&type=" + nearbyPlace);
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + API_KEY);
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());

    }
}
