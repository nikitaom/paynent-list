package main.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Danny Briskin (sql.coach.kiev@gmail.com)
 *         on  7/8/17 for webService project.
 */
@Entity
@Table(name = "oper_payments")
@Component
public class Payment extends ResourceSupport {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer paymentId;

    // to avoid infinite recursion in JSON master-detail-master...
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID", foreignKey = @ForeignKey(name = "fk_customer_payment"))
    private User user;

    @JsonIgnore
    @Column(nullable = false)
    private Date paymentDate;

    @Column(name = "paymentAmount")
    private Float paymentAmount;

    @Column
    private String channel;

    public Payment() {
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPaymentDateAsString(String paymentDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            this.paymentDate = simpleDateFormat.parse(paymentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPaymentDateAsString () {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return  simpleDateFormat.format(this.paymentDate);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (user != null ? !user.equals(payment.user) : payment.user != null) return false;
        if (paymentDate != null ? !paymentDate.equals(payment.paymentDate) : payment.paymentDate != null) return false;
        if (paymentAmount != null ? !paymentAmount.equals(payment.paymentAmount) : payment.paymentAmount != null)
            return false;
        return channel != null ? channel.equals(payment.channel) : payment.channel == null;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (paymentDate != null ? paymentDate.hashCode() : 0);
        result = 31 * result + (paymentAmount != null ? paymentAmount.hashCode() : 0);
        result = 31 * result + (channel != null ? channel.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + paymentId +
                ", user=" + user.getUserId() +
                ", paymentDate=" + paymentDate +
                ", paymentAmount=" + paymentAmount +
                ", channel='" + channel + '\'' +
                '}';
    }
}
