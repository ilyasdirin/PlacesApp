package com.idirin.placesapp.ui.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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

public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    protected OttoEventBus bus;

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    private Unbinder unbinder;
    protected boolean mTwoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        DaggerDiComponent.builder()
                .appModule(new AppModule(getApplication()))
                .diModule(new DiModule())
                .build().inject(this);

        if (findViewById(R.id.toolbar) != null) {
            mTwoPane = true;
        }

        unbinder = ButterKnife.bind(this);

        initToolbar();

        if (savedInstanceState == null) {
            init();
        } else {
            restore(savedInstanceState);
        }
    }


    public void replaceFragment(@IdRes int container, Fragment fragment, @Nullable Bundle bundle, boolean addBackStack) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        if (addBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        transaction.replace(container, fragment, fragment.getClass().getSimpleName());
        //transaction.setTransition(Constants.DEFAULT_FRAGMENT_TRANSITION_TYPE);
        transaction.commit();
    }

    public void addFragment(@IdRes int container, @NonNull Fragment fragment, @Nullable Bundle bundle, boolean addBackStack) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        if (addBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        transaction.add(container, fragment, fragment.getClass().getSimpleName());
        //transaction.setTransition(Constants.DEFAULT_FRAGMENT_TRANSITION_TYPE);
        transaction.commit();
    }


    @Override
    protected void onResume() {
        super.onResume();

        bus.register(this);
    }

    @Override
    protected void onDestroy() {

        unbinder.unbind();

        bus.unregister(this);

        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (mTwoPane) {
            this.finish();
        } else {
            if (getFragmentManager().getBackStackEntryCount() < 2) {
                this.finish();
            } else {
                super.onBackPressed();
            }
        }
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

    protected void setTitleText(@StringRes int stringResId) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null && toolbar != null) {
            actionBar.setTitle("");
            actionBar.setDisplayShowTitleEnabled(false);
            TextView txtToolbarTitle = toolbar.findViewById(R.id.txtToolbarTitle);
            txtToolbarTitle.setText(getString(stringResId));
        }
    }

    protected void setDetailText(@NonNull String detailText) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null && toolbar != null) {
            TextView txtToolbarDetail = toolbar.findViewById(R.id.txtToolbarDetail);
            txtToolbarDetail.setText(detailText);
            txtToolbarDetail.setVisibility(View.VISIBLE);
        }
    }

    protected void setDisplayHomeAsUpEnabled(boolean isEnabled) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(isEnabled);
            actionBar.setHomeButtonEnabled(isEnabled);
            actionBar.setHomeAsUpIndicator(R.drawable.action_back);
        }
    }




    public abstract @LayoutRes int getLayoutResId();
    public abstract @StringRes int getTitleResId();
    public abstract void init();
    public abstract void restore(@NonNull Bundle savedInstanceState);

}
