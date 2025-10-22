package com.aditya.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "turf")
public class Turf {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String name;
  private String location;

  @OneToMany(mappedBy = "turf", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TimeSlot> availableTimeSlots;
}
