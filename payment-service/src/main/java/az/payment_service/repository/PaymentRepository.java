package az.payment_service.repository;

import az.payment_service.model.Payment;
import az.payment_service.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findByBookingIdAndStatus(Long bookingId, PaymentStatus status);
}
