package com.hotel.controllers;

import com.hotel.models.Payment;
import com.hotel.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        if (payment.getBooking() != null && payment.getBooking().getId() != null) {
            java.util.Optional<Payment> existing = paymentRepository.findByBookingId(payment.getBooking().getId());
            if (existing.isPresent()) {
                Payment p = existing.get();
                p.setIsPaid(payment.getIsPaid());
                p.setTotalAmount(payment.getTotalAmount());
                p.setPaymentDate(payment.getPaymentDate());
                return paymentRepository.save(p);
            }
        }
        return paymentRepository.save(payment);
    }
}
