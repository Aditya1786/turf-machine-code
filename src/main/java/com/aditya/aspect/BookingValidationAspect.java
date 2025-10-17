package com.aditya.aspect;

import com.aditya.model.Turf;
import com.aditya.service.TurfService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BookingValidationAspect {
  TurfService turfService;

  public BookingValidationAspect(TurfService turfService) {
    this.turfService = turfService;
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
    Turf turf =
        turfService.getAllTurfs().stream()
            .filter(t -> t.getId().equals(turfId))
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException("Turf with ID " + turfId + " does not exist."));

    if (!turf.getAvailableTimeSlots().containsKey(timeSlotId)) {
      throw new IllegalArgumentException(
          "Time Slot ID " + timeSlotId + " is already booked for Turf ID " + turfId);
    }
  }
}
