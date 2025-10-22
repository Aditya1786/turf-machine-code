package com.aditya.service;

import com.aditya.model.Booking;
import com.aditya.model.TimeSlot;
import com.aditya.model.Turf;
import com.aditya.model.User;
import com.aditya.repository.BookingRepository;
import com.aditya.repository.TimeSlotRepository;
import com.aditya.repository.TurfRepository;
import com.aditya.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
  private final BookingRepository bookingRepository;
  private final TurfRepository turfRepository;
  private final UserRepository userRepository;
  private final TimeSlotRepository timeSlotRepository;

  public BookingService(
      BookingRepository bookingRepository,
      TurfRepository turfRepository,
      UserRepository userRepository,
      TimeSlotRepository timeSlotRepository) {
    this.userRepository = userRepository;
    this.bookingRepository = bookingRepository;
    this.turfRepository = turfRepository;
    this.timeSlotRepository = timeSlotRepository;
  }

  @Transactional
  public Booking bookSlot(String userName, String turfId, String timeSlotId) {
    // find or create user
    User user =
        userRepository
            .findByName(userName)
            .orElseGet(() -> userRepository.save(new User(null, userName)));

    // find turf
    Turf turf =
        turfRepository
            .findById(turfId)
            .orElseThrow(() -> new NoSuchElementException("Turf not found"));

    // find time slot
    TimeSlot timeSlot =
        timeSlotRepository
            .findByIdAndTurfIdAndAvailableTrue(timeSlotId, turfId)
            .orElseThrow(() -> new NoSuchElementException("Time slot not Available"));

    // create Booking
    Booking booking = new Booking(null, user, turf, timeSlot, LocalDate.now());
    bookingRepository.save(booking);
    timeSlot.setAvailable(false);
    timeSlotRepository.save(timeSlot);
    return booking;
  }

  public List<Booking> getBookings(String userId) {
    return bookingRepository.findByUserId(userId);
  }

  public List<Booking> getAllBookings() {
    return bookingRepository.findAll();
  }
}
