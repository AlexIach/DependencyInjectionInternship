package com.example.dependencyinjectionproject.repository.dao;

import java.util.List;

import com.example.dependencyinjectionproject.model.User;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface UserDao {

  @Insert
  void isert(User user);

  @Update
  void update(User user);

  @Delete
  void selete(User user);

  @Query("SELECT * FROM user")
  List<User> getAllUsers();

  @Query("SELECT * FROM user WHERE userId = :userId")
  User getUserById(String userId);

}
