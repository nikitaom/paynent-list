package main.dto;

import main.entity.Payment;
import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * @author Danny Briskin (sql.coach.kiev@gmail.com)
 *         on  18.07.2017 for webService project.
 */
public class Assembler {

    private static volatile Assembler instance;

    public static Assembler getInstance() {
        if (instance == null) {
            synchronized (Assembler.class) {
                if (instance == null) {
                    instance = new Assembler();
                }
            }
        }
        return instance;
    }

    public List<PaymentDTO> getPaymentDToList(List<Payment> paymentList) {
        List<PaymentDTO> paymentDTOList = new ArrayList<PaymentDTO>();
        for (Payment payment : paymentList) {
            paymentDTOList.add(new PaymentDTO(payment));
        }
        return  paymentDTOList;
    }

//    public PaymentDTO getPaymentDTO (Payment payment) {
//        Link selfLink = linkTo(methodOn(AppController.class)
//                .getPayment(payment.getPaymentId())).slash(payment.getId()).withSelfRel();
//        PaymentDTO paymentDTO =new PaymentDTO(payment);
//        paymentDTO.add(selfLink);
//        return  paymentDTO;
//    }

}
