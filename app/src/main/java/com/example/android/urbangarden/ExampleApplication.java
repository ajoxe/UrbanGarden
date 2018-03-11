package com.example.android.urbangarden;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by bobbybah on 3/9/18.
 */

public class ExampleApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }
}