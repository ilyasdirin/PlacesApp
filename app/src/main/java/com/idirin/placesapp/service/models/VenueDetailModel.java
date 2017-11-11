package com.idirin.placesapp.service.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by
 * idirin on 10/11/2017...
 */

public class VenueDetailModel {

    @SerializedName("venue")
    @Expose
    private VenueModel venue;

    public VenueModel getVenue() {
        return venue;
    }

    public void setVenue(VenueModel venue) {
        this.venue = venue;
    }
}
