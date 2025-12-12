package az.payment_service.service;

import az.payment_service.messaging.producer.PaymentEventProducer;
import az.payment_service.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentEventProducer eventProducer;

    @Transactional
    public void processPayment() {

    }

    @Transactional
    public void deletePayment() {

    }

    @Transactional
    public void refundPayment() {

    }
}
