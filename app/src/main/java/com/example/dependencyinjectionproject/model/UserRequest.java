package com.example.dependencyinjectionproject.model;

import org.joda.time.DateTime;

public class UserRequest {

  private User user;
  private DateTime dateTime;

  public UserRequest(User user) {
    this.user = user;
    dateTime = new DateTime();
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public DateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(DateTime dateTime) {
    this.dateTime = dateTime;
  }
}
