package com.aditya.repository;

import com.aditya.model.Booking;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class BookingRepository {
  Map<String, Booking> bookings;

  public BookingRepository() {
    this.bookings = new LinkedHashMap<>();
  }

  public void save(Booking booking) {
    bookings.put(booking.getId(), booking);
  }

  public Booking getById(String id) {
    return bookings.get(id);
  }

  public Collection<Booking> getAll() {
    return bookings.values();
  }
}
