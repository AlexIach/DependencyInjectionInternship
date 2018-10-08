package com.example.dependencyinjectionproject.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class User {

  @PrimaryKey
  @NonNull
  private String userId;
  private String userName;
  private String userSurname;
  private int userAge;
  private String userEmail;

  public User() {
  }

  @Ignore
  public User(String userId, String userName, String userSurname, int userAge, String userEmail) {
    this.userId = userId;
    this.userName = userName;
    this.userSurname = userSurname;
    this.userAge = userAge;
    this.userEmail = userEmail;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserSurname() {
    return userSurname;
  }

  public void setUserSurname(String userSurname) {
    this.userSurname = userSurname;
  }

  public int getUserAge() {
    return userAge;
  }

  public void setUserAge(int userAge) {
    this.userAge = userAge;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getUserInformation() {
    return "User Is is" + this.getUserId() + " Username is " + this.getUserName() + " User Surname is " + this.getUserSurname() +
      " User age is " + this.getUserAge() + " User e-mail is " + this.getUserEmail();
  }
}
