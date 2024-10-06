package com.example.expsense_tracker.Repo;

import com.example.expsense_tracker.entity.ExpenseEntity;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<ExpenseEntity, Long> {
    List<ExpenseEntity> findByDate(LocalDate date);
    List<ExpenseEntity> findByPaymentMethodAndDate(String paymentMethod, LocalDate date);
    List<ExpenseEntity> findByPaymentMethod(String paymentMethod);
    List<ExpenseEntity> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
