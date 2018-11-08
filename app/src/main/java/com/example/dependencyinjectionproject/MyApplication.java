package com.example.dependencyinjectionproject;

import javax.inject.Inject;

import com.example.dependencyinjectionproject.di.Injector;
import com.squareup.picasso.Picasso;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class MyApplication extends Application {

  public static final String TAG = "MyApplication";

  @Inject
  Context applicationContext;
  @Inject
  Picasso picasso;

  @Override
  public void onCreate() {
    super.onCreate();
    initDependencies();
    Log.d(TAG, "applicationContext in MyApplication class is " + applicationContext);
    Log.d(TAG, "picasso in MyApplication class is " + picasso);
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
