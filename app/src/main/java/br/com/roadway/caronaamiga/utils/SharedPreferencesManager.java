package br.com.roadway.caronaamiga.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;

public class SharedPreferencesManager {

    static SharedPreferences mSharedPreferences;

    public static void applyUserCurrentLocationPreference(Context mContext, String locationKey, Location currentLocation) {
        mSharedPreferences = mContext.getSharedPreferences(locationKey, mContext.MODE_PRIVATE);

        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(locationKey, currentLocation.getLatitude() + "," + currentLocation.getLongitude());
        mEditor.apply();
    }

    public static Location getUserCurrentLocationPreference(Context mContext, String locationKey) {

        mSharedPreferences = mContext.getSharedPreferences(locationKey, mContext.MODE_PRIVATE);

        Location mCurrentLocation = null;

        String coordinatesString = mSharedPreferences.getString(locationKey, "");

        if (!coordinatesString.equalsIgnoreCase("")) {
            int indexOfcomma = coordinatesString.lastIndexOf(',');

            String latitude = coordinatesString.substring(0, indexOfcomma);
            String longitude = coordinatesString.substring(indexOfcomma + 1, coordinatesString.length());

            mCurrentLocation = new Location("");

            mCurrentLocation.setLatitude(Double.parseDouble(latitude));
            mCurrentLocation.setLongitude(Double.parseDouble(longitude));
        }

        return mCurrentLocation;
    }
}
