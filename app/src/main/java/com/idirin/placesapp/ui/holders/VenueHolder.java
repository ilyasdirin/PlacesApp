package com.idirin.placesapp.ui.holders;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idirin.placesapp.R;
import com.idirin.placesapp.ui.listeners.RecyclerViewTouchListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by
 * idirin on 10/11/2017...
 */

public class VenueHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {

    @BindView(R.id.imViewCategory)
    public ImageView imViewCategory;
    @BindView(R.id.txtVenue)
    public TextView txtVenue;
    @BindView(R.id.txtRate)
    public TextView txtRate;
    @BindView(R.id.rlView)
    public RelativeLayout rlView;

    /*
        Touch listener is used instead of click listener for providing
        clicked coordinates to reveal effect for start position.
        In other situations simply click listener can be used.
     */
    private RecyclerViewTouchListener touchListener;

    public VenueHolder(View v, RecyclerViewTouchListener touchListener) {
        super(v);
        ButterKnife.bind(this, v);
        rlView.setOnTouchListener(this);
        this.touchListener = touchListener;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            touchListener.recyclerViewItemTouched(view, this.getAdapterPosition(), (int)motionEvent.getX(), (int)motionEvent.getY());
        }
        return true;
    }
}
