package br.com.roadway.caronaamiga.model;

import android.location.Location;

public class StopPoint {

    private String title;
    private Location pointLocation;
    private int imageResourceId;

    public StopPoint(String title, Location pointLocation, int imageResourceId) {
        this.title = title;
        this.pointLocation = pointLocation;
        this.imageResourceId = imageResourceId;
    }

    public Location getPointLocation() {
        return pointLocation;
    }

    public void setPointLocation(Location pointLocation) {
        this.pointLocation = pointLocation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
