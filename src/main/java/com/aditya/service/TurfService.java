package com.aditya.service;

import com.aditya.model.TimeSlot;
import com.aditya.model.Turf;
import com.aditya.repository.TurfRepository;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class TurfService {
  private final TurfRepository turfRepository;

  public TurfService(TurfRepository turfRepository) {
    this.turfRepository = turfRepository;
  }

  public Turf addTurf(String name, String location) {
    String id = "turf-" + (turfRepository.getAll().size() + 1);
    Map<String, TimeSlot> availableTimeSlots = initializeTimeSlots();
    Turf turf = new Turf(id, name, location, availableTimeSlots);
    turfRepository.save(turf);
    return turf;
  }

  public void bookTimeSlot(String turfId, String timeSlotId) {
    Turf turf = turfRepository.getById(turfId);
    if (turf != null) {
      turf.getAvailableTimeSlots().remove(timeSlotId);
    } else {
      throw new NoSuchElementException("Turf with ID " + turfId + " not found.");
    }
  }

  public List<Turf> searchTurfs(Map<String, String> filters) {
    return turfRepository.getAll().stream()
        .filter(
            turf -> {
              if (filters.containsKey("name")
                  && !turf.getName().equalsIgnoreCase(filters.get("name"))) {
                return false;
              }
              if (filters.containsKey("location")
                  && !turf.getLocation().equalsIgnoreCase(filters.get("location"))) {
                return false;
              }
              if (filters.containsKey("id") && !turf.getId().equalsIgnoreCase(filters.get("id"))) {
                return false;
              }
              return true;
            })
        .toList();
  }

  public List<Turf> getAllTurfs() {
    return turfRepository.getAll().stream().toList();
  }

  public List<TimeSlot> getAvailableTimeSlots(String turfId) {
    Turf turf = turfRepository.getById(turfId);
    return turf.getAvailableTimeSlots().values().stream().toList();
  }

  private Map<String, TimeSlot> initializeTimeSlots() {
    // Initialize time slots from 6 AM to 10 PM with 1-hour intervals
    Map<String, TimeSlot> timeSlots = new java.util.HashMap<>();
    for (int hour = 6; hour < 22; hour++) {
      String slotId = "slot-" + hour;
      TimeSlot slot =
          new TimeSlot(
              slotId, java.time.LocalTime.of(hour, 0), java.time.LocalTime.of(hour + 1, 0));
      timeSlots.put(slotId, slot);
    }
    return timeSlots;
  }
}
