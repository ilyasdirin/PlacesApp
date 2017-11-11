package com.idirin.placesapp.ui.mvp.venue_list;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.idirin.placesapp.service.models.VenueItemModel;
import com.idirin.placesapp.service.models.VenueModel;
import com.idirin.placesapp.ui.holders.VenueHolder;
import com.idirin.placesapp.ui.mvp.BaseMvp;

import java.util.List;

/**
 * Created by
 * idirin on 10/11/2017...
 */

public interface VenueListMvp {

    /**
     * Required View methods available to Presenter.
     * A passive layer, responsible to show data
     * and receive user interactions
     * Presenter to View
     */
    interface ViewOps extends BaseMvp.ViewOps {

        void showToast(@NonNull String msg);
        void showLoading(boolean showEnabled);
        void notifyDataSetChanged();
        void notifyDataRangeInserted(int startPosition, int itemCount);
    }

    /**
     * Operations offered to View to communicate with Presenter.
     * Process user interaction, sends data requests to Model, etc.
     * View to Presenter
     */
    interface ViewPresenterOps extends BaseMvp.ViewPresenterOps {

        VenueListMvp.ViewOps getView();
        void loadVenues();
        int getVenueCount();

        VenueHolder createViewHolder(ViewGroup parent, int viewType);
        void bindViewHolder(VenueHolder holder, int position);

        VenueListState getState();
        void setState(VenueListState state);
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

        List<VenueItemModel> getVenues();
        VenueModel getVenue(int position);
        int getVenueCount();
        void appendVenues(@NonNull List<VenueItemModel> venues);
        void setVenueSelected(int position, boolean isSelected);
        boolean getVenueSelected(int position);
    }
}
