package com.idirin.placesapp.event;

import com.idirin.placesapp.service.models.VenueModel;

/**
 * Created by
 * idirin on 11/11/2017...
 */

public class VenueSelectedEvent {

    private VenueModel venue;

    public VenueSelectedEvent(VenueModel venue) {
        this.venue = venue;
    }

    public VenueModel getVenue() {
        return venue;
    }
}
