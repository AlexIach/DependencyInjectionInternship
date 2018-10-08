package com.example.dependencyinjectionproject.di.components;

import javax.inject.Singleton;

import com.example.dependencyinjectionproject.MyApplication;
import com.example.dependencyinjectionproject.di.modules.ApplicationModule;
import com.example.dependencyinjectionproject.di.modules.NetworkModule;
import com.example.dependencyinjectionproject.di.modules.RepositoryModule;
import com.example.dependencyinjectionproject.repository.AppDatabase;
import com.example.dependencyinjectionproject.repository.network.WeatherRestApi;
import com.squareup.picasso.Picasso;

import android.content.Context;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, RepositoryModule.class})
public interface ApplicationComponent {

  void inject(MyApplication myApplication);

  Context context();

  Picasso picasso();

  AppDatabase appDatabase();

  WeatherRestApi weatherRestApi();

}
