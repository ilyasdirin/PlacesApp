package com.idirin.placesapp.event;

import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by
 * idirin on 08/11/2017.
 */

public class OttoEventBus {

    private static Bus bus;

    public OttoEventBus() {
        if (bus == null) {
            bus = new Bus(ThreadEnforcer.MAIN, OttoEventBus.class.getName());
        }
    }

    public void register(final Object o) {
        try {
            bus.register(o);
        } catch (final Throwable th) {
            Log.e("register failed", " " + th);
        }
    }

    public void unregister(final Object o) {
        try {
            bus.unregister(o);
        } catch (final Throwable th) {
            Log.e("unregister failed", " " + th);
        }
    }

    public void post(final Object e) {
        try {
            bus.post(e);
        } catch (final Throwable th) {
            Log.e("Post failed", " " + th);
        }
    }
}
