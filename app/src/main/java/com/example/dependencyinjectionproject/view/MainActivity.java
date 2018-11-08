package com.example.dependencyinjectionproject.view;

import java.util.List;

import javax.inject.Inject;

import com.example.dependencyinjectionproject.R;
import com.example.dependencyinjectionproject.di.Injector;
import com.example.dependencyinjectionproject.model.User;
import com.example.dependencyinjectionproject.presenter.MainActivityPresenter;
import com.example.dependencyinjectionproject.presenter.MainActivityPresenter.IMyCallback;
import com.example.dependencyinjectionproject.repository.AppDatabase;
import com.example.dependencyinjectionproject.repository.network.WeatherRestApi;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements IMyCallback {

  public static final String TAG = "MainActivity";


  private EditText editTextUserId;
  private EditText editTextUserName;
  private EditText editTextUserSurname;
  private EditText editTextUserAge;
  private EditText editTextUserEmail;
  private EditText editTextForUserIdInput;

  private Button buttonSaveUser;
  private Button buttonGetAlluser;
  private Button buttonGetUserById;

  @Inject
  Context applicationContext;
  @Inject
  Picasso picasso;
  @Inject
  MainActivityPresenter mainActivityPresenter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initViews();

    Injector.INSTANCE.initMainComponent(this);
    Injector.INSTANCE.getMainComponent().inject(this);

    Log.d(TAG, "applicationContext is " + applicationContext);
    Log.d(TAG, "picasso is " + picasso);
    Log.d(TAG, "mainActivityPresenter is " + mainActivityPresenter);

    mainActivityPresenter.setIMyCallback(this);


    buttonSaveUser.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        User user = new User(editTextUserId.getText().toString(),
          editTextUserName.getText().toString(),
          editTextUserSurname.getText().toString(),
          Integer.parseInt(editTextUserAge.getText().toString()),
          editTextUserEmail.getText().toString());

        mainActivityPresenter.saveDataInRoom(user);
        Toast.makeText(getApplicationContext(), "User is saved", Toast.LENGTH_LONG).show();
      }
    });

    buttonGetAlluser.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        mainActivityPresenter.getAllUsers();
      }
    });

    buttonGetUserById.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        mainActivityPresenter.getUserById(editTextForUserIdInput.getText().toString());
      }
    });

  }

  @Override
  public void onUserGetSuccesfully(User user) {
    Log.d(TAG, user.getUserInformation());
    Toast.makeText(this, "User information  " + user.getUserInformation(), Toast.LENGTH_LONG).show();
  }

  @Override
  public void onGetAllUserSuccesfully(List<User> userList) {
    Log.d(TAG, "userList size is " + userList.size());
    Toast.makeText(this, "userList size is " + userList.size(), Toast.LENGTH_LONG).show();
  }

  @Override
  public void onUserGetFailed(Throwable throwable) {
    Log.d(TAG, throwable.getLocalizedMessage());
    Toast.makeText(this, "Throwable message  " + throwable.getLocalizedMessage(), Toast.LENGTH_LONG).show();
  }

  private void initViews() {
    editTextUserId = findViewById(R.id.editTextUserId);
    editTextUserName = findViewById(R.id.editTextUserName);
    editTextUserSurname = findViewById(R.id.editTextUserSurname);
    editTextUserAge = findViewById(R.id.editTextUserAge);
    editTextUserEmail = findViewById(R.id.editTextUserEmail);
    editTextForUserIdInput = findViewById(R.id.editTextForUserIdInput);

    buttonSaveUser = findViewById(R.id.buttonSaveUser);
    buttonGetAlluser = findViewById(R.id.buttonGetAllUsers);
    buttonGetUserById = findViewById(R.id.buttonGetUserById);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Injector.INSTANCE.releaseMainComponent();
  }
}
