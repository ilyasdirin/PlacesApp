package com.idirin.placesapp.ui.mvp.venue_list;


import android.support.annotation.NonNull;

import com.idirin.placesapp.service.models.VenueItemModel;
import com.idirin.placesapp.service.models.VenueModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * idirin on 10/11/2017...
 */

public class VenueListModel implements VenueListMvp.ModelOps {

    private VenueListMvp.ModelPresenterOps mPresenter;
    private List<VenueItemModel> venues;

    public VenueListModel(VenueListMvp.ModelPresenterOps presenter) {
        mPresenter = presenter;
        venues = new ArrayList<>();
    }

    @Override
    public List<VenueItemModel> getVenues() {
        return venues;
    }

    @Override
    public VenueModel getVenue(int position) {
        return venues.get(position).venue;
    }

    @Override
    public int getVenueCount() {
        return venues.size();
    }

    @Override
    public void appendVenues(@NonNull List<VenueItemModel> venues) {
        this.venues.addAll(venues);
    }

    @Override
    public void setVenueSelected(int position, boolean isSelected) {
        venues.get(position).venue.isSelected = isSelected;
    }

    @Override
    public boolean getVenueSelected(int position) {
        return venues.get(position).venue.isSelected;
    }
}
