package com.aditya.model;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class TimeSlot {
  private final String id;
  private final LocalTime startTime;
  private final LocalTime endTime;
}
