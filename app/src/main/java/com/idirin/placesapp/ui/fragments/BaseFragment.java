package com.idirin.placesapp.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idirin.placesapp.R;
import com.idirin.placesapp.di.AppModule;
import com.idirin.placesapp.di.DaggerDiComponent;
import com.idirin.placesapp.di.DiModule;
import com.idirin.placesapp.event.OttoEventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by
 * idirin on 09/10/2017...
 */

public abstract class BaseFragment extends Fragment {

    protected AppCompatActivity activity;
    protected View view;
    protected Unbinder unbinder;

    @Inject
    protected OttoEventBus bus;

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(getLayoutResId(), container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        activity = (AppCompatActivity)getActivity();
        view = getView();

        initToolbar();

        DaggerDiComponent.builder()
                .appModule(new AppModule(getActivity().getApplication()))
                .diModule(new DiModule())
                .build().inject(this);

        setupMvp();
        setupViews();

        if (savedInstanceState == null) {
            init();
        } else {
            restore(savedInstanceState);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        bus.register(this);
    }

    @Override
    public void onDestroyView() {

        unbinder.unbind();

        bus.unregister(this);

        super.onDestroyView();
    }



    private void initToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getTitleResId() != 0) {
                setTitleText(getTitleResId());
            } else {
                setTitleText(0);
            }
        }
    }

    protected void setSupportActionBar(Toolbar toolbar) {
        activity.setSupportActionBar(toolbar);
    }

    protected void setTitleText(@StringRes int stringResId) {
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null && toolbar != null) {
            actionBar.setTitle("");
            actionBar.setDisplayShowTitleEnabled(false);
            if (stringResId > 0) {
                TextView txtToolbarTitle = toolbar.findViewById(R.id.txtToolbarTitle);
                txtToolbarTitle.setText(activity.getString(stringResId));
            }
        }
    }

    protected void setDetailText(@NonNull String detailText) {
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null && toolbar != null) {
            TextView txtToolbarDetail = toolbar.findViewById(R.id.txtToolbarDetail);
            txtToolbarDetail.setText(detailText);
            txtToolbarDetail.setVisibility(View.VISIBLE);
        }
    }

    protected void setDisplayHomeAsUpEnabled(boolean isEnabled) {
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null && toolbar != null) {
            actionBar.setDisplayHomeAsUpEnabled(isEnabled);
            actionBar.setHomeButtonEnabled(isEnabled);
            actionBar.setHomeAsUpIndicator(R.drawable.action_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onBackPressed();
                }
            });
        }
    }





    protected abstract @LayoutRes int getLayoutResId();
    protected abstract @StringRes int getTitleResId();
    protected abstract void setupMvp();
    protected abstract void setupViews();
    protected abstract void init();
    protected abstract void restore(@NonNull Bundle savedInstanceState);

}
