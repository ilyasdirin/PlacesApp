package com.idirin.placesapp.ui.mvp.venue_detail;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.idirin.placesapp.BuildConfig;
import com.idirin.placesapp.di.AppModule;
import com.idirin.placesapp.di.DaggerDiComponent;
import com.idirin.placesapp.di.DiModule;
import com.idirin.placesapp.service.RestInterface;
import com.idirin.placesapp.service.response.VenueDetailResponse;
import com.idirin.placesapp.ui.holders.VenueHolder;
import com.idirin.placesapp.ui.mvp.BaseMvp;

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
 * idirin on 11/11/2017...
 */

public class VenueDetailPresenter implements VenueDetailMvp.ViewPresenterOps, VenueDetailMvp.ModelPresenterOps {

    @Inject
    RestInterface api;

    private WeakReference<VenueDetailMvp.ViewOps> mView;
    private VenueDetailMvp.ModelOps mModel;

    private CompositeDisposable subscriptions = new CompositeDisposable();

    public VenueDetailPresenter(VenueDetailMvp.ViewOps view) {
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
        mModel = (VenueDetailMvp.ModelOps)model;
    }

    @Override
    public VenueDetailMvp.ViewOps getView() {
        return mView.get();
    }

    @Override
    public void loadVenueDetail(@NonNull String venueId) {

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

        api.getVenueDetail(venueId, params)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VenueDetailResponse>() {

                    @Override
                    public void onNext(VenueDetailResponse response) {
                        mModel.setVenueDetail(response.getResponse().getVenue());
                        getView().setVenue(mModel.getVenueDetail());
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
    public VenueDetailState getState() {
        return new VenueDetailState(mModel.getVenueDetail());
    }

    @Override
    public void setState(VenueDetailState state) {
        mModel.setVenueDetail(state.getVenue());
        getView().setVenue(mModel.getVenueDetail());
    }


}
