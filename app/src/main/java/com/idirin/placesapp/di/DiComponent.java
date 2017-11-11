package com.idirin.placesapp.di;


import com.idirin.placesapp.ui.activities.BaseActivity;
import com.idirin.placesapp.ui.fragments.BaseFragment;
import com.idirin.placesapp.ui.mvp.venue_detail.VenueDetailPresenter;
import com.idirin.placesapp.ui.mvp.venue_list.VenueListPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by
 * idirin on 09/11/2017.
 */

@Singleton
@Component(modules = {AppModule.class, DiModule.class})
public interface DiComponent {

    void inject(BaseActivity baseActivity);

    void inject(BaseFragment baseFragment);

    void inject(VenueListPresenter venueListPresenter);

    void inject(VenueDetailPresenter venueDetailPresenter);

}
