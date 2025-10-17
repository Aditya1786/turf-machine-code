package com.aditya.controller;

import com.aditya.model.TimeSlot;
import com.aditya.model.Turf;
import com.aditya.service.TurfService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/turfs")
public class TurfController {
  private final TurfService turfService;

  public TurfController(TurfService turfService) {
    this.turfService = turfService;
  }

  @PostMapping("/add")
  public ResponseEntity<Turf> addTurf(@RequestBody Map<String, String> turfData) {
    String name = turfData.get("name");
    String location = turfData.get("location");
    return ResponseEntity.ok(turfService.addTurf(name, location));
  }

  @GetMapping("/all")
  public ResponseEntity<List<Turf>> getAllTurfs() {
    return ResponseEntity.ok(turfService.getAllTurfs());
  }

  @GetMapping("/search")
  public ResponseEntity<List<Turf>> search(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String location,
      @RequestParam(required = false) String id) {
    Map<String, String> filters = new HashMap<>();
    if (name != null) {
      filters.put("name", name);
    }
    if (location != null) {
      filters.put("location", location);
    }
    if (id != null) {
      filters.put("id", id);
    }
    return ResponseEntity.ok(turfService.searchTurfs(filters));
  }

  @GetMapping("/slots")
  public ResponseEntity<List<TimeSlot>> getAvailableTimeSlots(@RequestParam String turfId) {
    return ResponseEntity.ok(turfService.getAvailableTimeSlots(turfId));
  }
}
