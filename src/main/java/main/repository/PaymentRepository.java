package main.repository;

import main.entity.Payment;
import main.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query("SELECT p FROM main.entity.Payment p "
            + " WHERE p.user.id = :id")
    List<Payment> getUserPayments(@Param("id") int id);

    @Query("SELECT p FROM main.entity.Payment p "
            + " WHERE p.user.id = :user_id and p.id = :id")
    Payment getUserPayment(@Param("id") int id, @Param("user_id") int userId);

    Page<Payment> findByUser(User user, Pageable pageable);

    Page<Payment> findAll(Pageable pageable);
}



