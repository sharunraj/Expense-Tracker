package com.example.expsense_tracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Expense_Table")
public class ExpenseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "Desc")
    private String description;
    @Column(name = "Expense")
    private double amount;
    @Column(name = "Date")
    private LocalDate date;
    @Column(name = "Income")
    private boolean income;
    @Column(name = "PaymentMethod")
    private String paymentMethod;

}
