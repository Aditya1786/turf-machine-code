package com.aditya.service;

import com.aditya.model.Booking;
import com.aditya.model.User;
import com.aditya.repository.BookingRepository;
import com.aditya.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
  private final BookingRepository bookingRepository;
  private final TurfService turfService;
  private final UserRepository userRepository;

  public BookingService(
      BookingRepository bookingRepository, TurfService turfService, UserRepository userRepository) {
    this.userRepository = userRepository;
    this.bookingRepository = bookingRepository;
    this.turfService = turfService;
  }

  public Booking bookSlot(String userName, String turfId, String timeSlotId) {
    // Logic to create and save a booking
    String bookingId = "booking-" + (bookingRepository.getAll().size() + 1);
    String userId = "user-" + userName.hashCode();
    LocalDate date = LocalDate.now();

    // create and save user if not exists
    User user = new User(userId, userName);
    userRepository.save(user);

    // create and save booking
    Booking booking = new Booking(bookingId, userId, turfId, timeSlotId, date);
    bookingRepository.save(booking);
    turfService.bookTimeSlot(turfId, timeSlotId);
    return booking;
  }

  public List<Booking> getAllBookings() {
    return bookingRepository.getAll().stream().toList();
  }
}
