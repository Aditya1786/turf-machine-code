package com.aditya.controller;

import com.aditya.model.Booking;
import com.aditya.service.BookingService;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
  private final BookingService bookingService;

  public BookingController(BookingService bookingService) {
    this.bookingService = bookingService;
  }

  @PostMapping("/book")
  public ResponseEntity<Booking> bookSlot(@RequestBody Map<String, String> bookingData) {
    String userName = bookingData.get("userName");
    String turfId = bookingData.get("turfId");
    String timeSlotId = bookingData.get("timeSlotId");
    return ResponseEntity.ok(bookingService.bookSlot(userName, turfId, timeSlotId));
  }

  @GetMapping("/all")
  public ResponseEntity<List<Booking>> getAllBookings() {
    return ResponseEntity.ok(bookingService.getAllBookings());
  }
}
