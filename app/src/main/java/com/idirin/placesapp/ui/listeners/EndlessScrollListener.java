package com.idirin.placesapp.ui.listeners;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.idirin.placesapp.utils.Constants;

/**
 * Created by
 * idirin on 10/11/2017.
 */

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener{

    private boolean loading = true;
    private int previousTotal = 0;

    public LinearLayoutManager linearLayoutManager;

    public EndlessScrollListener(LinearLayoutManager linearLayoutManager){
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = linearLayoutManager.getItemCount();
        int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }

        int visibleThreshold = Constants.ENDLESS_SCROLL_LOAD_MORE_THRESHOLD;
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)){
            loading = true;
            onLoadMore();
        }
    }

    public abstract void onLoadMore();

}
