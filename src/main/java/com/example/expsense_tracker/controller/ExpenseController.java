package com.example.expsense_tracker.controller;

import com.example.expsense_tracker.dto.ExpenseDto;
import com.example.expsense_tracker.entity.ExpenseEntity;
import com.example.expsense_tracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class ExpenseController {
    @Autowired
    ExpenseService expenseService;

    @PostMapping("/expenses")
    public ResponseEntity<String> addExpense(@Valid @RequestBody ExpenseDto expenseDto){
        ExpenseEntity expenseEntity = expenseService.saveExpense(expenseDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Expense with ID:"+ expenseEntity.getId());
    }

    @GetMapping("/expenses/{date}")
    public ResponseEntity<Double> getTotalAmountByDay(@PathVariable LocalDate date)  {
        double amount =  expenseService.getTotalAmountByDay(date);
        return  ResponseEntity.ok(amount);
    }

    @GetMapping("/expenses/{startDate}/{endDate}")
    public ResponseEntity<Double> getMonthlyExpense(@PathVariable LocalDate startDate,@PathVariable LocalDate endDate){
        double amount  = expenseService.getMonthlyExpense(startDate, endDate);
        return ResponseEntity.ok(amount);
    }
    @GetMapping("/expenses")
    public ResponseEntity<Double> getCurrentBalance(){
        double amount = expenseService.getCurrentBalance();
        return ResponseEntity.ok(amount);
    }
    @GetMapping("/expenses/payment-method/{paymentMethod}")
    public ResponseEntity<Double> getExpenseByPaymentMethod(String paymentMethod){
        double amount = expenseService.getExpenseByPaymentMethod(paymentMethod);
        return  ResponseEntity.ok(amount);
    }
}
