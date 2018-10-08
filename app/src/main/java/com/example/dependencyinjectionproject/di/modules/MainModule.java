package com.example.dependencyinjectionproject.di.modules;


import com.example.dependencyinjectionproject.di.scopes.ScopeMain;
import com.example.dependencyinjectionproject.presenter.MainActivityPresenter;
import com.example.dependencyinjectionproject.repository.AppDatabase;
import com.example.dependencyinjectionproject.repository.network.WeatherRestApi;
import com.example.dependencyinjectionproject.view.MainActivity;

import android.content.Context;
import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

  private MainActivity mainActivity;

  public MainModule(MainActivity mainActivity) {
    this.mainActivity = mainActivity;
  }

  @Provides
  @ScopeMain
  public MainActivityPresenter provideScreenNavigation(Context context, AppDatabase appDatabase, WeatherRestApi weatherRestApi) {
    return new MainActivityPresenter(context, appDatabase, weatherRestApi);
  }
}
