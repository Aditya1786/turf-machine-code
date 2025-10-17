package com.aditya.repository;

import com.aditya.model.Turf;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class TurfRepository {
  private final Map<String, Turf> turfs;

  public TurfRepository() {
    this.turfs = new LinkedHashMap<>();
  }

  public void save(Turf turf) {
    turfs.put(turf.getId(), turf);
  }

  public Turf getById(String id) {
    return turfs.get(id);
  }

  public Collection<Turf> getAll() {
    return turfs.values();
  }
}
