package com.example.dependencyinjectionproject.presenter;

import java.util.List;

import com.example.dependencyinjectionproject.model.User;
import com.example.dependencyinjectionproject.repository.AppDatabase;
import com.example.dependencyinjectionproject.repository.network.WeatherRestApi;

import android.content.Context;

public class MainActivityPresenter {

  private Context context;
  private AppDatabase appDatabase;
  private WeatherRestApi weatherRestApi;
  private IMyCallback iMyCallback;

  public MainActivityPresenter() {
  }

  public MainActivityPresenter(Context context, AppDatabase appDatabase, WeatherRestApi weatherRestApi) {
    this.context = context;
    this.appDatabase = appDatabase;
    this.weatherRestApi = weatherRestApi;
  }

  public void setIMyCallback(IMyCallback iMyCallback) {
    this.iMyCallback = iMyCallback;
  }

  public void saveDataInRoom(User user) {
    appDatabase.getUserDao().isert(user);
  }

  public void getAllUsers() {
    iMyCallback.onGetAllUserSuccesfully(appDatabase.getUserDao().getAllUsers());
  }

  public void getUserById(String userId) {
    iMyCallback.onUserGetSuccesfully(appDatabase.getUserDao().getUserById(userId));
  }



  public interface IMyCallback {
    void onUserGetSuccesfully(User user);

    void onUserGetFailed(Throwable throwable);

    void onGetAllUserSuccesfully(List<User> userList);

  }
}
