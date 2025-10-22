package com.aditya.model;

import jakarta.persistence.*;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "time_slot")
public class TimeSlot {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private LocalTime startTime;
  private LocalTime endTime;
  private boolean available;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "turf_id")
  private Turf turf;
}
