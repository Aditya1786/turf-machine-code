package com.aditya.service;

import com.aditya.model.TimeSlot;
import com.aditya.model.Turf;
import com.aditya.repository.TimeSlotRepository;
import com.aditya.repository.TurfRepository;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class TurfService {
  private final TurfRepository turfRepository;
  private final TimeSlotRepository timeSlotRepository;

  public TurfService(TurfRepository turfRepository, TimeSlotRepository timeSlotRepository) {
    this.turfRepository = turfRepository;
    this.timeSlotRepository = timeSlotRepository;
  }

  public Turf addTurf(String name, String location) {
    List<TimeSlot> slots = initializeTimeSlots();
    Turf turf = new Turf(null, name, location, slots);
    slots.forEach(slot -> slot.setTurf(turf));
    return turfRepository.save(turf);
  }

  public List<Turf> searchTurfs(Map<String, String> filters) {
    List<Turf> turfs = turfRepository.findAll();
    return turfs.stream()
        .filter(
            turf -> {
              for (Map.Entry<String, String> entry : filters.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                switch (key) {
                  case "name":
                    return turf.getName().equalsIgnoreCase(value);
                  case "location":
                    return turf.getLocation().equalsIgnoreCase(value);
                  case "id":
                    return turf.getId().equalsIgnoreCase(value);
                  default:
                    return false;
                }
              }
              // all filters matched
              return true;
            })
        .toList();
  }

  public List<Turf> getAllTurfs() {
    return turfRepository.findAll();
  }

  public List<TimeSlot> getAvailableTimeSlots(String turfId) {
    List<TimeSlot> slots = timeSlotRepository.findByTurfIdAndAvailableTrue(turfId);
    if (slots.isEmpty()) {
      // This means either turf doesn’t exist OR no available slots exist
      // If you want to specifically check if turf exists:
      if (!turfRepository.existsById(turfId)) {
        throw new NoSuchElementException("Turf with ID " + turfId + " not found.");
      }
      throw new NoSuchElementException("No available slots for Turf " + turfId);
    }
    return slots;
  }


  private List<TimeSlot> initializeTimeSlots() {
    List<TimeSlot> slots = new ArrayList<>();
    for (int hour = 6; hour < 23; hour++) {
      slots.add(new TimeSlot(null, LocalTime.of(hour, 0), LocalTime.of(hour + 1, 0), true, null));
    }
    return slots;
  }
}
