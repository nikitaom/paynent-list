package main.service;

import main.dto.PaymentDTO;
import main.entity.Payment;
import main.entity.User;
import main.repository.PaymentRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Danny Briskin (sql.coach.kiev@gmail.com)
 * on  7/8/17 for webService project.
 */
@Service(value = "paymentService")
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private UserRepository userRepository;


    public void addPayment(Payment payment) {

        paymentRepository.saveAndFlush(payment);
    }

    @Override
    public Integer makePayment(User user, PaymentDTO paymentDTO) {
        if (user == null) {
            return -1;
        } else {
            Payment payment = new Payment();
            payment.setUser(user);
            payment.setPaymentAmount(paymentDTO.getPaymentAmount());
            payment.setPaymentDateAsString(paymentDTO.getPaymentDate());
            payment.setChannel(paymentDTO.getChannel());

            this.addPayment(payment);
            return payment.getPaymentId();
        }
    }

    @Override
    public Integer makePayment(User user, Payment payment) {
        if (user == null) {
            return -1;
        } else {
            Payment newPayment = new Payment();
            newPayment.setPaymentId(payment.getPaymentId());
            newPayment.setUser(user);
            newPayment.setPaymentAmount(payment.getPaymentAmount());
            newPayment.setPaymentDate(payment.getPaymentDate());
            newPayment.setChannel(payment.getChannel());

            this.addPayment(newPayment);


            return newPayment.getPaymentId();
        }
    }

    public Payment getPayment(int id) {
        return paymentRepository.findOne(id);

    }

    @Override
    public Payment getPayment(int id, int customerID) {
        return paymentRepository.getUserPayment(id, customerID);
    }

    @Override
    public List<Payment> getAllPayments() {

        return paymentRepository.findAll();
    }

    @Override
    public List<Payment> getCustomerPayments(User user) {
        return paymentRepository.getUserPayments(user.getUserId());
    }

    @Override
    public Integer create(PaymentDTO resource) {
        User user = userRepository.findOne(resource.getUserId());
        return this.makePayment(user, resource);
    }

    @Override
    public void updatePayment(Payment payment, PaymentDTO paymentDTO) {
        if (payment != null) {
            payment.setPaymentAmount(paymentDTO.getPaymentAmount());
            payment.setPaymentDateAsString(paymentDTO.getPaymentDate());
            payment.setChannel(paymentDTO.getChannel());
            User user = userRepository.findOne(paymentDTO.getUserId());
            if (user != null) {
                payment.setUser(user);
            }
            paymentRepository.saveAndFlush(payment);
        }
    }

    @Override
    public void updatePayment(Payment payment, Payment newPayment) {
        if (payment != null) {
            payment.setPaymentAmount(newPayment.getPaymentAmount());
            payment.setPaymentDate(newPayment.getPaymentDate());
            payment.setChannel(newPayment.getChannel());
            User user = newPayment.getUser();
            if (user != null) {
                payment.setUser(user);
            }
            paymentRepository.saveAndFlush(payment);
        }
    }

    @Override
    public void deletePayment(int id) {
        paymentRepository.delete(id);
        paymentRepository.flush();
    }

    @Override
    public Page<Payment> findAll(Pageable pageable) {
        return paymentRepository.findAll(pageable);
    }

    @Override
    public Integer create(Payment payment) {
        // TODO stub to create payment without customer
        User user = userRepository.findOne(1);
        return this.makePayment(user, payment);

    }


}
