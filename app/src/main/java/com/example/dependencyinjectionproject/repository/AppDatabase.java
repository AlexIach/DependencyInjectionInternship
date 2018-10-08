package com.example.dependencyinjectionproject.repository;

import com.example.dependencyinjectionproject.model.User;
import com.example.dependencyinjectionproject.repository.dao.UserDao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
  public abstract UserDao getUserDao();
}
