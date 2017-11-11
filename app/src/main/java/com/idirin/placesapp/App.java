package com.idirin.placesapp;

import android.app.Application;

import com.idirin.placesapp.di.AppModule;
import com.idirin.placesapp.di.DaggerDiComponent;
import com.idirin.placesapp.di.DiModule;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by
 * idirin on 09/11/2017...
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerDiComponent.builder()
                .appModule(new AppModule(this))
                .diModule(new DiModule())
                .build();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
