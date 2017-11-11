package com.idirin.placesapp.ui.mvp;

import android.content.Context;

/**
 * Created by idirin on 10/11/2017.
 * Base Mvp methods to be extended to all mvp interfaces
 */

public interface BaseMvp {

    /**
     * Required View methods available to Presenter.
     * A passive layer, responsible to show data
     * and receive user interactions
     *      Presenter to View
     */
    interface ViewOps {

        void setupMvp();
        Context getActivityContext();
    }

    /**
     * Operations offered to View to communicate with Presenter.
     * Process user interaction, sends data requests to Model, etc.
     *      View to Presenter
     */
    interface ViewPresenterOps {

        void onDestroy();
    }

    /**
     * Operations offered to Model to communicate with Presenter
     * Handles all data business logic.
     *      Presenter to Model
     */
    interface ModelPresenterOps {

        Context getActivityContext();
        <T extends ModelOps> void setModel(T model);
    }

    /**
     * Required Presenter methods available to Model.
     *      Model to Presenter
     */
    interface ModelOps {

    }


}
