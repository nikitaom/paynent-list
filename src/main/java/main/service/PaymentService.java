package main.service;

import main.dto.PaymentDTO;
import main.entity.Payment;
import main.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

/**
 * @author Danny Briskin (sql.coach.kiev@gmail.com)
 *         on  7/8/17 for webService project.
 */
public interface PaymentService {
    void addPayment(Payment payment);
    Integer makePayment(User user, PaymentDTO payment);


    Integer makePayment(User user, Payment payment);

    Payment getPayment(int id);
    Payment getPayment(int id, int customerID);

    List<Payment> getAllPayments();
    List<Payment> getCustomerPayments(User user);

    Integer create(PaymentDTO resource);

    void updatePayment(Payment payment, PaymentDTO paymentDTO);
    void updatePayment(Payment payment, Payment newPayment);

    void deletePayment(int id);

    Page<Payment> findAll(Pageable pageable);

    Integer create(Payment payment);
}
