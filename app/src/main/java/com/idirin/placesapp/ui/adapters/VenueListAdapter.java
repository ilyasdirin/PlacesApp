package com.idirin.placesapp.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.idirin.placesapp.ui.holders.VenueHolder;
import com.idirin.placesapp.ui.mvp.venue_list.VenueListMvp;


/**
 * Created by
 * idirin on 10/11/2017...
 */

public class VenueListAdapter extends RecyclerView.Adapter<VenueHolder> {

    private VenueListMvp.ViewPresenterOps mPresenter;

    public VenueListAdapter(@NonNull VenueListMvp.ViewPresenterOps presenter) {
        mPresenter = presenter;
    }

    @Override
    public VenueHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mPresenter.createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(VenueHolder holder, int position) {
        mPresenter.bindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getVenueCount();
    }

}
