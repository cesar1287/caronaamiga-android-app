package br.com.roadway.caronaamiga.view;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.roadway.caronaamiga.R;
import br.com.roadway.caronaamiga.adapter.StopPointAdapter;
import br.com.roadway.caronaamiga.model.StopPoint;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ChooseStopPointActivity extends AppCompatActivity {

    @Bind(R.id.stop_points_recyclerview)
    RecyclerView stopPointsRecyclerView;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private List<StopPoint> stopPointsDataSet;
    private String rideType;

    public static List<Location> stopPointsLocations;

    public static int[] stopPointsImagesResources = {
            R.drawable.lago_agronomia_ct, R.drawable.central_cc, R.drawable.sti,
            R.drawable.r3, R.drawable.fisica, R.drawable.edfisica, R.drawable.matematica,
            R.drawable.ru, R.drawable.central_hmonte, R.drawable.lagoa_agronomia_hmonte,
            R.drawable.hmonte
    };

    public static double[] stopPointsLatitudes = {
            -3.7387132, -3.7387132, -3.7387132, -3.7387132, -3.7387132, -3.7387132,
            -3.7387132, -3.7387132, -3.7387132, -3.7387132, -3.7387132
    };

    public static double[] stopPointsLongitudes = {
            -3.7387132, -3.7387132, -3.7387132, -3.7387132, -3.7387132, -3.7387132,
            -3.7387132, -3.7387132, -3.7387132, -3.7387132, -3.7387132
    };

    public static String[] stopPointsTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_choose_stop_point);

        ButterKnife.bind(this);

        Intent mIntent = getIntent();

        rideType =  mIntent.getStringExtra(MainActivity.RIDE_KEY);

        setupDataSet();

        setupRecyclerView();

        setupToolbar();

    //    double [] coordinates = SharedPreferencesManager.getUserCurrentLocationPreference(this, MainActivity.LOCATION_KEY);
    }

    private void setupDataSet() {
        setupLocationStopPoints();

        stopPointsTitles = getResources().getStringArray(R.array.stop_points_titles);

        stopPointsDataSet = new ArrayList<>();

        for (int i = 0; i < stopPointsImagesResources.length; i++) {
            stopPointsDataSet.add(new StopPoint(stopPointsTitles[i], stopPointsLocations.get(i), stopPointsImagesResources[i]));
        }
    }

    private void setupLocationStopPoints() {
        stopPointsLocations = new ArrayList<>();
        Location mLocation = new Location("");

        for (int i = 0; i < stopPointsImagesResources.length; i++) {
            mLocation.setLatitude(stopPointsLatitudes[i]);
            mLocation.setLongitude(stopPointsLongitudes[i]);
            stopPointsLocations.add(mLocation);
        }
    }

    private void setupToolbar() {
        if (mToolbar != null) {

            int currentSdkLevel = android.os.Build.VERSION.SDK_INT;
            if (currentSdkLevel > 19) {
                mToolbar.setElevation(7);
            }

            mToolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            if (rideType.equals(MainActivity.OFFER_RIDE_KEY)) {
                mToolbar.setTitle(R.string.offer_ride);
            }

            if (rideType.equals(MainActivity.REQUEST_RIDE_KEY)) {
                mToolbar.setTitle(R.string.destnation_point);
            }
        }
    }

    private void setupRecyclerView() {
        StopPointAdapter mStopPointAdapter = new StopPointAdapter(stopPointsDataSet, ChooseStopPointActivity.this, rideType);
        stopPointsRecyclerView.setHasFixedSize(true);
        stopPointsRecyclerView.setAdapter(mStopPointAdapter);

        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(ChooseStopPointActivity.this);
        mLinearLayoutManagerVertical.setOrientation(LinearLayout.VERTICAL);
        stopPointsRecyclerView.setLayoutManager(mLinearLayoutManagerVertical);
    }
}