package com.example.dependencyinjectionproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.example.dependencyinjectionproject.model.Car;
import com.example.dependencyinjectionproject.model.User;
import com.example.dependencyinjectionproject.model.UserRequest;
import com.example.dependencyinjectionproject.presenter.MainActivityPresenter;
import com.example.dependencyinjectionproject.presenter.MainActivityPresenter.IMyCallback;
import com.example.dependencyinjectionproject.repository.AppDatabase;
import com.example.dependencyinjectionproject.repository.dao.UserDao;
import com.example.dependencyinjectionproject.repository.network.WeatherRestApi;
import com.example.dependencyinjectionproject.utils.StringUtils;
import com.example.dependencyinjectionproject.utils.TestUtils;

import android.content.Context;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({StringUtils.class})
public class MainActivityPreenterTest {

  @Mock
  WeatherRestApi weatherRestApi;
  @Mock
  UserDao userDao;
  @Mock
  IMyCallback iMyCallback;
  @Mock
  private Context context;
  @Mock
  private AppDatabase appDatabase;
  @Mock
  private UserRequest userRequest;

  private MainActivityPresenter mainActivityPresenter;
  private Car car;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    when(appDatabase.getUserDao()).thenReturn(userDao);
    mainActivityPresenter = new MainActivityPresenter(context, appDatabase, weatherRestApi);
    mainActivityPresenter.setIMyCallback(iMyCallback);
  }

  /*****************************************
   Test to check if specific method is called
   ******************************************/
  @Test
  public void testGetAllUsers() {
    mainActivityPresenter.getAllUsers();
    verify(iMyCallback).onGetAllUserSuccesfully(new ArrayList<User>());
  }

  /*****************************************
   Test to check if specific method is called
   ******************************************/
  @Test
  public void testGetUserById() {
    mainActivityPresenter.getUserById("userId");
    verify(iMyCallback).onUserGetSuccesfully((User) anyObject());
  }

  @Test
  public void testModifyUsersByUserNameToDisplay() {
    List<User> userList = new ArrayList<>();
    User userOne = mock(User.class);
    User userTwo = mock(User.class);
    User userThree = mock(User.class);

    when(userOne.getUserAge()).thenReturn(10);
    when(userTwo.getUserAge()).thenReturn(12);
    when(userThree.getUserAge()).thenReturn(22);

    userList.add(userOne);
    userList.add(userTwo);
    userList.add(userThree);
    when(userDao.getAllUsers()).thenReturn(userList);
    when(context.getString(R.string.child)).thenReturn("Child");
    when(context.getString(R.string.teenager)).thenReturn("Teenager");
    when(context.getString(R.string.adult)).thenReturn("Adult");

    Map<String, User> userHashMap = new TreeMap<>();
    userHashMap = mainActivityPresenter.getUserStatuses();

    assertEquals(context.getString(R.string.adult), userHashMap.keySet().toArray()[0]);
    assertEquals(context.getString(R.string.child), userHashMap.keySet().toArray()[1]);
    assertEquals(context.getString(R.string.teenager), userHashMap.keySet().toArray()[2]);
  }

  @Test
  public void testIsUserRequestFilled() {
    User userOne = mock(User.class);
    when(userOne.getUserName()).thenReturn("John");
    when(userOne.getUserSurname()).thenReturn("Doe");
    when(userRequest.getDateTime()).thenReturn(new DateTime());
    when(userRequest.getUser()).thenReturn(userOne);

    assertTrue(mainActivityPresenter.isUserRequestFilled(userRequest));
  }

  @Test
  public void testIsUserRequestNotFilled() {
    User userOne = mock(User.class);
    when(userOne.getUserName()).thenReturn("John");
    when(userOne.getUserSurname()).thenReturn("Doe");
    when(userRequest.getDateTime()).thenReturn(null);
    when(userRequest.getUser()).thenReturn(userOne);

    assertFalse(mainActivityPresenter.isUserRequestFilled(userRequest));
  }

  @Test
  public void testSmth() {
    car = TestUtils.readJson("my_car", Car.class);
    System.out.println(car.printCarInformation());
    //NOTE we ca not use Log in test calsses
    // TODO test logic here
  }

  @Test
  public void testStaticmethods() {
    User userOne = mock(User.class);
    when(userOne.getUserName()).thenReturn(null);

    PowerMockito.mockStatic(StringUtils.class);
    PowerMockito.when(StringUtils.isStringNullOrEmpty(anyString())).thenCallRealMethod();

    assertTrue(mainActivityPresenter.isUserNameAvailable(userOne));
  }
}
