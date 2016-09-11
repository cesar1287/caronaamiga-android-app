package br.com.roadway.caronaamiga.controller;

import android.content.Intent;
import android.location.Location;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import br.com.roadway.caronaamiga.R;
import br.com.roadway.caronaamiga.model.StopPoint;
import br.com.roadway.caronaamiga.utils.SharedPreferencesManager;
import butterknife.Bind;
import butterknife.ButterKnife;

public class RideFinalMessageActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.final_message_image)
    ImageView finalMessageImage;

    @Bind(R.id.final_message)
    TextView finalMessage;

    private String rideType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ride_final_message_activity);

        ButterKnife.bind(this);

        Intent mIntent = getIntent();

        rideType =  mIntent.getStringExtra(MainActivity.RIDE_KEY);

        setupToolbar();

        setupUi();

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
                mToolbar.setTitle(R.string.thx);
            }

            if (rideType.equals(MainActivity.REQUEST_RIDE_KEY)) {
                mToolbar.setTitle(R.string.just_wait_ride);
            }
        }
    }

    private void setupUi() {
        if (rideType.equals(MainActivity.OFFER_RIDE_KEY)) {
            Glide.with(RideFinalMessageActivity.this)
                    .load("")
                    .placeholder(R.drawable.offer_ride_final)
                    .into(finalMessageImage);

            finalMessage.setText(R.string.offer_ride_message);
        }

        if (rideType.equals(MainActivity.REQUEST_RIDE_KEY)) {
            int closerStopPointImageId = calculateCloserStopPoint();

            Glide.with(RideFinalMessageActivity.this)
                    .load("")
                    .placeholder(ChooseStopPointActivity.stopPointsImagesResources[closerStopPointImageId])
                    .into(finalMessageImage);

            StringBuilder message = new StringBuilder();

            message.append(getResources().getString(R.string.request_ride_message));
            message.append("\n");
            message.append(ChooseStopPointActivity.stopPointsTitles[closerStopPointImageId]);
            message.append("\n\n");
            message.append(getResources().getString(R.string.just_wait));

            finalMessage.setText(message);
        }
    }

    private int calculateCloserStopPoint() {
        Location currentLocation = SharedPreferencesManager.getUserCurrentLocationPreference(RideFinalMessageActivity.this, MainActivity.LOCATION_KEY);
        double minDistance = 1000;
        int closerStopPointIndex = 0;

        for (int i = 0; i < ChooseStopPointActivity.stopPointsLocations.size(); i++) {
            Location mLocation = ChooseStopPointActivity.stopPointsLocations.get(i);

            if (mLocation.distanceTo(currentLocation) < minDistance) {
                minDistance = mLocation.distanceTo(currentLocation);
                closerStopPointIndex = i;
            }
        }

        return closerStopPointIndex;
    }
}
