package com.idirin.placesapp.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.idirin.placesapp.R;
import com.idirin.placesapp.event.VenueSelectedEvent;
import com.idirin.placesapp.ui.mvp.venue_detail.VenueDetailFragment;
import com.idirin.placesapp.ui.mvp.venue_list.VenueListFragment;
import com.idirin.placesapp.utils.Constants;
import com.squareup.otto.Subscribe;

/**
 * Created by
 * idirin on 09/10/2017...
 */

public class MainActivity extends BaseActivity {

    private String venueName;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public int getTitleResId() {
        return R.string.app_name;
    }

    @Override
    public void init() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constants.ARG_TWO_PANE, mTwoPane);
        replaceFragment(R.id.frameLayout, new VenueListFragment(), bundle, true);
    }

    @Override
    public void restore(@NonNull Bundle savedInstanceState) {
        venueName = savedInstanceState.getString(Constants.ARG_VENUE_NAME);
        if (venueName != null) {
            setDetailText(venueName);
        }
    }



    @Subscribe
    public void onVenueSelected(VenueSelectedEvent event) {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
            replaceFragment(R.id.frameDetail, VenueDetailFragment.newInstance(event.getVenue().id, mTwoPane), null, true);
        } else {
            addFragment(R.id.frameDetail, VenueDetailFragment.newInstance(event.getVenue().id, mTwoPane), null, true);
        }
        venueName = event.getVenue().name;
        setDetailText(venueName);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.ARG_VENUE_NAME, venueName);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        venueName = null;
    }

}
