package com.example.dependencyinjectionproject.di.modules;

import javax.inject.Singleton;

import com.example.dependencyinjectionproject.repository.AppDatabase;

import android.arch.persistence.room.Room;
import android.content.Context;
import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
  public static final String DATA_BASE_NAME = "myDataBase";

  @Provides
  @Singleton
  AppDatabase provideAppDatabase(Context context) {
    return Room.databaseBuilder(context, AppDatabase.class, DATA_BASE_NAME)
      // NOT RECOMMENDED allow Main Query
      .allowMainThreadQueries()
      .build();
  }

}
