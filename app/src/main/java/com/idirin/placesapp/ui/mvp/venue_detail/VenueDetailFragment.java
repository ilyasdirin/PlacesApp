package com.idirin.placesapp.ui.mvp.venue_detail;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.idirin.placesapp.R;
import com.idirin.placesapp.service.models.VenueModel;
import com.idirin.placesapp.ui.fragments.BaseFragment;
import com.idirin.placesapp.utils.Constants;

import butterknife.BindView;


public class VenueDetailFragment extends BaseFragment implements VenueDetailMvp.ViewOps {

    @BindView(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.txtVenueName)
    protected TextView txtVenueName;
    @BindView(R.id.txtRating)
    protected TextView txtRating;
    @BindView(R.id.imView)
    protected ImageView imView;
    @BindView(R.id.progressBar)
    protected ProgressBar progressBar;

    private VenueDetailMvp.ViewPresenterOps mPresenter;
    private VenueDetailState state;
    private String venueId;

    public static VenueDetailFragment newInstance(String venueId, boolean isTwoPane) {
        VenueDetailFragment fr = new VenueDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARG_VENUE_ID, venueId);
        fr.setArguments(bundle);
        return fr;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_venue_detail;
    }

    @Override
    protected int getTitleResId() {
        return R.string.app_name;
    }


    @Override
    protected void setupViews() {
        Bundle bundle = getArguments();
        venueId = bundle.getString(Constants.ARG_VENUE_ID);
        swipeRefreshLayout.setEnabled(false);
        setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void init() {
        if (!TextUtils.isEmpty(venueId)) {
            mPresenter.loadVenueDetail(venueId);
        }
    }

    @Override
    protected void restore(@NonNull Bundle savedInstanceState) {
        state = savedInstanceState.getParcelable(Constants.STATE_VENUE_DETAIL);
        mPresenter.setState(state);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        state = mPresenter.getState();
        /*
            Objects made parcelable instead of serializable, reasons for this is that
            we are being explicit about the serialization process instead of using reflection to infer it.
            It also stands to reason that the code has been heavily optimized for this purpose.
            Serializable uses Java Reflection which is a process of examining or modifying the run time behavior of a class at run time
            that causes slow process during runtime.
         */
        outState.putParcelable(Constants.STATE_VENUE_DETAIL, state);
    }

    @Override
    public void setupMvp() {
        VenueDetailPresenter presenter = new VenueDetailPresenter(this);
        VenueDetailModel model = new VenueDetailModel(presenter);
        presenter.setModel(model);
        mPresenter = presenter;
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    @Override
    public void showToast(@NonNull String msg) {
        Toast.makeText(getActivityContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(boolean showEnabled) {
        swipeRefreshLayout.setRefreshing(showEnabled);
    }

    @Override
    public void setVenue(@NonNull VenueModel venue) {
        txtVenueName.setText(venue.name);
        txtRating.setText(getString(R.string.rating, venue.rating));
        String imageUrl = venue.bestPhoto.prefix + "400x400" + venue.bestPhoto.suffix;
        Glide.with(getActivityContext()).load(imageUrl).into(imView);
        setDetailText(venue.name);

        int fillAnimDuration = 2000;
        ObjectAnimator.ofInt(progressBar, "progress", 0, (int)(venue.rating * 10))
                .setDuration(fillAnimDuration).start();
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDestroy();
        super.onDestroyView();
    }
}
