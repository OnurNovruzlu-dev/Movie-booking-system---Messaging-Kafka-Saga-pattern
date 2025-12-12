package az.booking_service.service;

import az.booking_service.dto.BookingRequest;
import az.booking_service.dto.BookingResponse;
import az.booking_service.event.BookingCancelledEvent;
import az.booking_service.event.BookingConfirmedEvent;
import az.booking_service.event.BookingCreatedEvent;
import az.booking_service.mapper.ConvertFromEntity;
import az.booking_service.mapper.ConvertToEntity;
import az.booking_service.messaging.producer.BookingEventProducer;
import az.booking_service.model.Booking;
import az.booking_service.repository.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {
    private final BookingRepository repository;
    private final BookingEventProducer bookingEventProducer;

    @Transactional
    public BookingResponse createBooking(BookingRequest request) {
        log.info("Creating booking for user: {}, movie: {}, seats: {}",
                request.getUserId(), request.getMovieId(), request.getSeatIds());

        Booking booking = ConvertToEntity.createBookingRequest(request);
        booking = repository.saveAndFlush(booking);

        log.info("Booking saved with ID: {} and status: {}",
                booking.getId(), booking.getStatus());

        BookingCreatedEvent event = ConvertFromEntity.bookingCreatedEvent(booking);

        // Store correlation ID for tracking
        booking.setCorrelationId(event.getCorrelationId());
        repository.save(booking);

        bookingEventProducer.publishBookingCreatedEvent(event);

        return ConvertFromEntity.bookingResponse_create(booking);
    }

    @Transactional
    public void confirmBooking(Long id, String correlationId) {
        log.info("Confirming booking: {}, correlationId: {}", id, correlationId);

        Booking booking = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found: " + id));

        booking.confirm();
        repository.save(booking);

        log.info("Booking confirmed: {}", booking.getId());

        bookingEventProducer.publishBookingConfirmedEvent(new BookingConfirmedEvent(id, correlationId));
    }

    @Transactional
    public void cancelBooking(Long id, String correlationId, String reason) {
        log.info("Cancelling booking: {}, correlationId: {}, reason: {}", id, correlationId, reason);

        Booking booking = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found: " + id));

        booking.cancel(reason);
        repository.save(booking);

        log.info("Booking cancelled: {}", booking.getId());

        bookingEventProducer.publishBookingCancelledEvent(new BookingCancelledEvent(id, correlationId, reason));
    }
}
