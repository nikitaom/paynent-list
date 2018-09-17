package main.dto;

import main.entity.Payment;
import org.springframework.hateoas.ResourceSupport;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Danny Briskin (sql.coach.kiev@gmail.com)
 *         on  16.07.2017 for webService project.
 */
public class PaymentDTO extends ResourceSupport {

    @NotNull(message = "Field customerId cannot be null")
    private Integer userId;

    @NotNull(message = "Field paymentAmount cannot be null")
    @Pattern(regexp = "[+]?[0-9]*\\.?[0-9]+", message = "Field paymentAmount cannot satisfy the pattern")
    private Float paymentAmount;

    @Pattern(regexp = "(^(((0[1-9]|1[0-9]|2[0-8])[\\-](0[1-9]|1[012]))|((29|30|31)[\\-](0[13578]|1[02]))|((29|30)[\\-](0[4,6,9]|11)))[\\-](19|[2-9][0-9])\\d\\d$)|(^29[\\-]02[\\-](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)", message = "Field paymentDate cannot satisfy the pattern dd-MM-yyyy")
    private String paymentDate;

    private String channel;

    public PaymentDTO() {
    }

    public PaymentDTO(Integer userId, Float paymentAmount, String paymentDate, String channel) {
        this.userId = userId;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.channel = channel;
    }

    public PaymentDTO(Payment payment) {
        this.userId = payment.getUser().getUserId();
        this.paymentAmount = payment.getPaymentAmount();
        this.paymentDate = payment.getPaymentDateAsString();
        this.channel = payment.getChannel();
    }

    public Integer getUserId() {
        return userId;
    }


    public Float getPaymentAmount() {
        return paymentAmount;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setPaymentAmount(Float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPaymentDate() {
        return paymentDate;
    }


    public String getChannel() {
        return channel;
    }

 }
