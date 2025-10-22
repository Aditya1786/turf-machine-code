package com.aditya.repository;

import com.aditya.model.Turf;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurfRepository extends JpaRepository<Turf, String> {
  Optional<Turf> findByName(String name);
}
