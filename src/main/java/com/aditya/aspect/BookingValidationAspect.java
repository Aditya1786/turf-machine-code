package com.aditya.aspect;

import com.aditya.model.Turf;
import com.aditya.repository.TimeSlotRepository;
import com.aditya.repository.TurfRepository;
import com.aditya.service.TurfService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BookingValidationAspect {
  TimeSlotRepository timeSlotRepository;
  TurfRepository turfRepository;

  public BookingValidationAspect(TimeSlotRepository timeSlotRepository, TurfRepository turfRepository) {
    this.timeSlotRepository = timeSlotRepository;
    this.turfRepository = turfRepository;
  }

  @Before(
      "execution(* com.aditya.service.BookingService.bookSlot(..)) && args(userName, turfId, timeSlotId)")
  public void validateBooking(String userName, String turfId, String timeSlotId) {
    if (userName == null) {
      throw new IllegalArgumentException("User Name cannot be null");
    }
    if (turfId == null) {
      throw new IllegalArgumentException("Turf ID cannot be null");
    }
    if (timeSlotId == null) {
      throw new IllegalArgumentException("Time Slot ID cannot be null");
    }

    // check if requested time slot is already booked for the turf
    if (timeSlotRepository.findByIdAndTurfIdAndAvailableTrue(timeSlotId, turfId).isEmpty()) {
      throw new IllegalArgumentException("Time Slot is already booked for the turf");
    }
  }
}
