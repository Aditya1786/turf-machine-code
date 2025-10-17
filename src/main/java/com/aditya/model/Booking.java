package com.aditya.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Booking {
  private final String id;
  private final String userId;
  private final String turfId;
  private final String timeSlotId;
  private final LocalDate date;
}
