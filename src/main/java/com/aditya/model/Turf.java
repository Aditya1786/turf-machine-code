package com.aditya.model;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Turf {
  private final String id;
  private final String name;
  private final String location;
  private final Map<String, TimeSlot> availableTimeSlots;
}
