package com.idirin.placesapp.ui.mvp.venue_detail;

import com.idirin.placesapp.service.models.VenueModel;

/**
 * Created by
 * idirin on 11/11/2017...
 */

public class VenueDetailModel implements VenueDetailMvp.ModelOps {

    private VenueDetailMvp.ModelPresenterOps mPresenter;
    private VenueModel venue;

    public VenueDetailModel(VenueDetailMvp.ModelPresenterOps presenter) {
        mPresenter = presenter;
    }

    @Override
    public VenueModel getVenueDetail() {
        return venue;
    }

    @Override
    public void setVenueDetail(VenueModel venue) {
        this.venue = venue;
    }
}
