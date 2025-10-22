package com.aditya.repository;

import com.aditya.model.Booking;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
  List<Booking> findByUserId(String userId);

  List<Booking> findByTurfId(String turfId);

  List<Booking> findByTurfIdAndDate(String turfId, LocalDate date);
}
