package com.idirin.placesapp.ui.mvp.venue_list;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.idirin.placesapp.R;
import com.idirin.placesapp.ui.adapters.VenueListAdapter;
import com.idirin.placesapp.ui.fragments.BaseFragment;
import com.idirin.placesapp.ui.listeners.EndlessScrollListener;
import com.idirin.placesapp.utils.Constants;

import butterknife.BindView;

/**
 * Created by
 * idirin on 09/10/2017...
 */

public class VenueListFragment extends BaseFragment implements VenueListMvp.ViewOps {

    private VenueListMvp.ViewPresenterOps mPresenter;
    private VenueListAdapter venueListAdapter;
    private boolean mTwoPane;

    @BindView(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_venue_list;
    }

    @Override
    protected int getTitleResId() {
        return R.string.app_name;
    }

    @Override
    public void setupMvp() {
        VenueListPresenter presenter = new VenueListPresenter(this);
        VenueListModel model = new VenueListModel(presenter);
        presenter.setModel(model);
        mPresenter = presenter;
    }

    @Override
    protected void setupViews() {
        Bundle bundle = getArguments();
        mTwoPane = bundle.getBoolean(Constants.ARG_TWO_PANE);

        swipeRefreshLayout.setEnabled(false);
        venueListAdapter = new VenueListAdapter(mPresenter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivityContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(venueListAdapter);
        recyclerView.addOnScrollListener(new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore() {
                mPresenter.loadVenues();
            }
        });
    }

    @Override
    protected void init() {
        mPresenter.loadVenues();
    }

    @Override
    protected void restore(@NonNull Bundle savedInstanceState) {
        VenueListState venueListState = savedInstanceState.getParcelable(Constants.STATE_VENUE_LIST);
        mPresenter.setState(venueListState);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDestroy();
        super.onDestroyView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        /*
            Objects made parcelable instead of serializable, reasons for this is that
            we are being explicit about the serialization process instead of using reflection to infer it.
            It also stands to reason that the code has been heavily optimized for this purpose.
            Serializable uses Java Reflection which is a process of examining or modifying the run time behavior of a class at run time
            that causes slow process during runtime.
         */
        outState.putParcelable(Constants.STATE_VENUE_LIST, mPresenter.getState());
    }






    /* MVP METHODS */

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
    public void notifyDataSetChanged() {
        venueListAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyDataRangeInserted(int startPosition, int itemCount) {
        venueListAdapter.notifyItemRangeInserted(startPosition, itemCount);
    }

}
