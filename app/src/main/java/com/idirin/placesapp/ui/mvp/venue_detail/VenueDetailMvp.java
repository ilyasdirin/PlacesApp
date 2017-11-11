package com.idirin.placesapp.ui.mvp.venue_detail;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.idirin.placesapp.service.models.VenueModel;
import com.idirin.placesapp.ui.holders.VenueHolder;
import com.idirin.placesapp.ui.mvp.BaseMvp;

/**
 * Created by
 * idirin on 11/11/2017...
 */

public interface VenueDetailMvp {

    /**
     * Required View methods available to Presenter.
     * A passive layer, responsible to show data
     * and receive user interactions
     * Presenter to View
     */
    interface ViewOps extends BaseMvp.ViewOps {

        void showToast(@NonNull String msg);
        void showLoading(boolean showEnabled);
        void setVenue(@NonNull VenueModel venue);
    }

    /**
     * Operations offered to View to communicate with Presenter.
     * Process user interaction, sends data requests to Model, etc.
     * View to Presenter
     */
    interface ViewPresenterOps extends BaseMvp.ViewPresenterOps {

        VenueDetailMvp.ViewOps getView();
        void loadVenueDetail(@NonNull String venueId);

        VenueDetailState getState();
        void setState(VenueDetailState state);
    }

    /**
     * Operations offered to Model to communicate with Presenter
     * Handles all data business logic.
     * Presenter to Model
     */
    interface ModelPresenterOps extends BaseMvp.ModelPresenterOps {


    }

    /**
     * Required Presenter methods available to Model.
     * Model to Presenter
     */
    interface ModelOps extends BaseMvp.ModelOps {

        VenueModel getVenueDetail();
        void setVenueDetail(VenueModel venue);
    }

}
