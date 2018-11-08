package com.example.dependencyinjectionproject.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.example.dependencyinjectionproject.R;
import com.example.dependencyinjectionproject.model.User;
import com.example.dependencyinjectionproject.model.UserRequest;
import com.example.dependencyinjectionproject.repository.AppDatabase;
import com.example.dependencyinjectionproject.repository.network.WeatherRestApi;
import com.example.dependencyinjectionproject.utils.StringUtils;

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

  public Map<String, User> getUserStatuses() {
    Map<String, User> userStatusesList = new TreeMap<>();

    for (User user : appDatabase.getUserDao().getAllUsers()) {

      if (user.getUserAge() <= 10) {
        userStatusesList.put(context.getString(R.string.child), user);
      } else if (user.getUserAge() <= 19) {
        userStatusesList.put(context.getString(R.string.teenager), user);
      } else {
        userStatusesList.put(context.getString(R.string.adult), user);
      }
    }
    return userStatusesList;
  }

  public boolean isUserRequestFilled(UserRequest userRequest) {
    return userRequest.getUser().getUserName() != null &&
      userRequest.getUser().getUserSurname() != null &&
      userRequest.getDateTime() != null;
  }


  public boolean isUserNameAvailable(User user) {
    return StringUtils.isStringNullOrEmpty(user.getUserName());
  }

  public interface IMyCallback {
    void onUserGetSuccesfully(User user);

    void onUserGetFailed(Throwable throwable);

    void onGetAllUserSuccesfully(List<User> userList);

  }
}
