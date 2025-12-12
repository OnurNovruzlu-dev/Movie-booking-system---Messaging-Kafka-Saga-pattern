package az.booking_service.controller;

import az.booking_service.dto.BookingRequest;
import az.booking_service.dto.BookingResponse;
import az.booking_service.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/booking")
public class BookingController {

    private final BookingService service;


    @PostMapping("/create")
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody BookingRequest request) {

        return ResponseEntity.status(201).body(service.createBooking(request));
    }
}
