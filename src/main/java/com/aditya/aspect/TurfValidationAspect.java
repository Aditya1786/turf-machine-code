package com.aditya.aspect;

import com.aditya.repository.TurfRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TurfValidationAspect {
  TurfRepository turfRepository;

  public TurfValidationAspect(TurfRepository turfRepository) {
    this.turfRepository = turfRepository;
  }

  @Before("execution(* com.aditya.service.TurfService.addTurf(..)) && args(turfName, location)")
  public void validateTurfDetails(String turfName, String location) {
    if (turfName == null || turfName.trim().isEmpty()) {
      throw new IllegalArgumentException("Turf name cannot be null or empty");
    }
    if (location == null || location.trim().isEmpty()) {
      throw new IllegalArgumentException("Turf location cannot be null or empty");
    }
  }

  @Before("execution(* com.aditya.service.TurfService.getAvailableTimeSlots(..)) && args(turfId)")
  public void validateTurfId(String turfId) {
    if (turfId == null || turfId.trim().isEmpty()) {
      throw new IllegalArgumentException("Turf ID cannot be null or empty");
    }
    if (turfRepository.getById(turfId) == null) {
      throw new IllegalArgumentException("Turf with ID " + turfId + " does not exist.");
    }
  }
}
