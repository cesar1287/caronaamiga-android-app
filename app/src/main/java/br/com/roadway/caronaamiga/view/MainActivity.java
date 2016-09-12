package br.com.roadway.caronaamiga.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import br.com.roadway.caronaamiga.R;
import br.com.roadway.caronaamiga.utils.SharedPreferencesManager;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.offer_ride_button)
    Button offerRideButton;

    @Bind(R.id.offer_ride_image)
    ImageView offerRideImage;

    @Bind(R.id.request_ride_button)
    Button requestRideButton;

    @Bind(R.id.request_ride_image)
    ImageView requestRideImage;

    private GoogleApiClient mGoogleApiClient;

    public static final String OFFER_RIDE_KEY = "OFFER";
    public static final String REQUEST_RIDE_KEY = "REQUEST";
    public static final String RIDE_KEY = "RIDE";
    public static final String LOCATION_KEY = "LOCATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupToolbar();

        setupUi();

        startGoogleApiConnection();
    }

    private void setupToolbar() {

        int currentSdkLevel = android.os.Build.VERSION.SDK_INT;

        if (mToolbar != null) {
            mToolbar.setTitle(R.string.app_name);
        }

        if (currentSdkLevel > 19) {
            mToolbar.setElevation(7);
        }
    }

    private void setupUi() {
        requestRideButton.setOnClickListener(MainActivity.this);
        Glide.with(MainActivity.this)
                .load("")
                .placeholder(ContextCompat.getDrawable(MainActivity.this, R.drawable.pedir))
                .into(requestRideImage);

        offerRideButton.setOnClickListener(MainActivity.this);
        Glide.with(MainActivity.this)
                .load("")
                .placeholder(ContextCompat.getDrawable(MainActivity.this, R.drawable.oferecer))
                .into(offerRideImage);
    }

    protected synchronized void startGoogleApiConnection() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onClick(View v) {

        Intent mIntent = new Intent(this, ChooseStopPointActivity.class);

        if (v.getId() == requestRideButton.getId()) {
            mIntent.putExtra(RIDE_KEY, REQUEST_RIDE_KEY);
        }

        if (v.getId() == offerRideButton.getId()) {
            mIntent.putExtra(RIDE_KEY, OFFER_RIDE_KEY);
        }

        startActivity(mIntent);
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mCurrentLocation != null) {
            SharedPreferencesManager.applyUserCurrentLocationPreference(MainActivity.this, LOCATION_KEY, mCurrentLocation);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
