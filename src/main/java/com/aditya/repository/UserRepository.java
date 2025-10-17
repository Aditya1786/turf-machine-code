package com.aditya.repository;

import com.aditya.model.User;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
  private final Map<String, User> users;

  public UserRepository() {
    this.users = new HashMap<>();
  }

  public void save(User user) {
    users.put(user.getId(), user);
  }

  public User findById(String id) {
    return users.get(id);
  }

  public Map<String, User> getAll() {
    return users;
  }
}
