package com.idirin.placesapp.ui.mvp.venue_list;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.idirin.placesapp.BuildConfig;
import com.idirin.placesapp.R;
import com.idirin.placesapp.di.AppModule;
import com.idirin.placesapp.di.DaggerDiComponent;
import com.idirin.placesapp.di.DiModule;
import com.idirin.placesapp.event.OttoEventBus;
import com.idirin.placesapp.event.VenueSelectedEvent;
import com.idirin.placesapp.service.RestInterface;
import com.idirin.placesapp.service.models.VenueModel;
import com.idirin.placesapp.service.response.VenueListResponse;
import com.idirin.placesapp.ui.holders.VenueHolder;
import com.idirin.placesapp.ui.listeners.RecyclerViewTouchListener;
import com.idirin.placesapp.ui.mvp.BaseMvp;
import com.idirin.placesapp.utils.Constants;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by
 * idirin on 10/11/2017...
 */

public class VenueListPresenter implements VenueListMvp.ViewPresenterOps, VenueListMvp.ModelPresenterOps {

    @Inject
    RestInterface api;

    @Inject
    OttoEventBus bus;

    private WeakReference<VenueListMvp.ViewOps> mView;
    private VenueListMvp.ModelOps mModel;
    private int pageNum;

    private CompositeDisposable subscriptions = new CompositeDisposable();

    private Pair<Integer, View> lastSelectedItem;

    VenueListPresenter(VenueListMvp.ViewOps view) {
        mView = new WeakReference<>(view);

        DaggerDiComponent.builder()
                .appModule(new AppModule((Application) getActivityContext().getApplicationContext()))
                .diModule(new DiModule())
                .build().inject(this);
    }



    @Override
    public void onDestroy() {
        subscriptions.clear();
        subscriptions.dispose();
        mModel = null;
    }

    @Override
    public Context getActivityContext() {
        return getView().getActivityContext();
    }

    @Override
    public <T extends BaseMvp.ModelOps> void setModel(T model) {
        mModel = (VenueListMvp.ModelOps)model;
    }

    @Override
    public VenueListMvp.ViewOps getView() {
        return mView.get();
    }

    @Override
    public void loadVenues() {

        /*
             APIs can be called directly in presenter layer in MVP
             but this process also can be called in model layer with a repository pattern
             The advantage of this method is that you can directly operate your business logic,
             complex operations, data transformation in the same layer.
         */


        subscriptions.clear();
        getView().showLoading(true);

        Map<String, Object> params = new HashMap<>();
        params.put("v", BuildConfig.API_VERSION);
        params.put("client_id", BuildConfig.CLIENT_ID);
        params.put("client_secret", BuildConfig.CLIENT_SECRET);
        params.put("intent", "checkin");
        params.put("ll", "41.0082,28.9784");
        params.put("radius", 100000);
        params.put("offset", pageNum * Constants.VENUE_COUNT_LIMIT);
        params.put("limit", Constants.VENUE_COUNT_LIMIT);

        api.getVenues(params)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VenueListResponse>() {

                    @Override
                    public void onNext(VenueListResponse response) {
                        if (response.getResponse().getGroups().get(0).getItems().size()>0) {
                            mModel.appendVenues(response.getResponse().getGroups().get(0).getItems());
                            if (pageNum == 0) {
                                getView().notifyDataSetChanged();
                            } else {
                                int responseVenueCount = response.getResponse().getGroups().get(0).getItems().size();
                                getView().notifyDataRangeInserted(mModel.getVenueCount()-responseVenueCount, responseVenueCount);
                            }
                            pageNum ++;
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        getView().showLoading(false);
                        mView.get().showToast(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        getView().showLoading(false);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        subscriptions.add(d);
                    }

                });
    }

    @Override
    public int getVenueCount() {
        return mModel.getVenueCount();
    }

    @Override
    public VenueHolder createViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_venue_list, parent, false);
        return new VenueHolder(v, new RecyclerViewTouchListener() {
            @Override
            public void recyclerViewItemTouched(View v, int position, int startX, int startY) {
                mModel.setVenueSelected(position, true);
                v.setSelected(true);

                if (lastSelectedItem != null) {
                    if (lastSelectedItem.first != position) {
                        lastSelectedItem.second.setSelected(false);
                        mModel.setVenueSelected(lastSelectedItem.first, false);
                    }
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ViewAnimationUtils.createCircularReveal(v,
                            startX,
                            startY,
                            0,
                            v.getHeight() * 2).start();
                }
                bus.post(new VenueSelectedEvent(mModel.getVenue(position)));
                lastSelectedItem = new Pair<>(position, v);
            }
        });
    }

    @Override
    public void bindViewHolder(VenueHolder holder, int position) {
        /*
            We bind the data with view in presenter layer to fit your business logic into view
            This process can be done in view layer
         */
        VenueModel venue = mModel.getVenue(position);
        holder.rlView.setSelected(venue.isSelected != null ? venue.isSelected : false);
        if (venue.isSelected != null && venue.isSelected) {
            lastSelectedItem = new Pair<>(position, (View)holder.rlView);
        }
        holder.txtVenue.setText(venue.name);
        holder.txtRate.setText(getActivityContext().getString(R.string.rating, venue.rating));
        String imageUrl = venue.categories.get(0).icon.prefix + "bg_64" + venue.categories.get(0).icon.suffix;
        Glide.with(getActivityContext()).load(imageUrl).into(holder.imViewCategory);
    }

    @Override
    public VenueListState getState() {
        return new VenueListState(mModel.getVenues(), pageNum);
    }

    @Override
    public void setState(VenueListState state) {
        pageNum = state.paging;
        mModel.appendVenues(state.venues);
        getView().notifyDataSetChanged();
    }
}
