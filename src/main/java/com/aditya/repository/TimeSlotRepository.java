package com.aditya.repository;

import com.aditya.model.TimeSlot;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, String> {
  Optional<TimeSlot> findByIdAndTurfIdAndAvailableTrue(String id, String turfId);

  List<TimeSlot> findByTurfIdAndAvailableTrue(String turfId);
}
