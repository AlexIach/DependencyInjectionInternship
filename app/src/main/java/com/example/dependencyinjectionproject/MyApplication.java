package com.example.dependencyinjectionproject;

import javax.inject.Inject;

import com.example.dependencyinjectionproject.di.Injector;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class MyApplication extends Application {

  public static final String TAG = "MyApplication";

  @Inject
  Context applicationContext;

  @Override
  public void onCreate() {
    super.onCreate();
    initDependencies();
    Log.d(TAG, "applicationContext in MyApplication class is " + applicationContext);
  }

  private void initDependencies() {
    Injector.INSTANCE.initApplicationComponent(this);
    Injector.INSTANCE.getApplicationComponent().inject(this);

  }

  @Override
  public void onTerminate() {
    super.onTerminate();
    Injector.INSTANCE.releaseApplicationComponent();
  }
}
