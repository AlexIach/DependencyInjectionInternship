package com.example.dependencyinjectionproject.di.modules;

import javax.inject.Singleton;

import com.example.dependencyinjectionproject.MyApplication;
import com.squareup.picasso.Picasso;

import android.content.Context;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

  private MyApplication myApplication;

  public ApplicationModule(MyApplication myApplication) {
    this.myApplication = myApplication;
  }

  @Provides
  @Singleton
  public Context provideApplicationContext() {
    return myApplication.getApplicationContext();
  }

  @Provides
  @Singleton
  Picasso providePicasso(Context context) {
    return Picasso.with(context);
  }
}
