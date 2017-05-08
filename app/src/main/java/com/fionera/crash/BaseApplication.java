package com.fionera.crash;

import android.app.Application;

import com.fionera.ctracker.util.DefaultCrashHandler;

/**
 * BaseApplication
 * Created by fionera on 17-5-8 in CrashTracker.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DefaultCrashHandler.getInstance().init(this);
    }
}
