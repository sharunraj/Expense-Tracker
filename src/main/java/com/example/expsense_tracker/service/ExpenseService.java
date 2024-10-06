package com.example.expsense_tracker.service;

import com.example.expsense_tracker.Repo.ExpenseRepo;
import com.example.expsense_tracker.dto.ExpenseDto;
import com.example.expsense_tracker.entity.ExpenseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    ExpenseRepo expenseRepo;

    public ExpenseEntity saveExpense(ExpenseDto expenseDto){
        ExpenseEntity expenseEntity = convertToEntity(expenseDto);
        return expenseRepo.save(expenseEntity);
    }

    public ExpenseEntity convertToEntity(ExpenseDto expenseDto){
        ExpenseEntity expenseEntity = new ExpenseEntity();
        expenseEntity.setDescription(expenseDto.getDescription());
        expenseEntity.setPaymentMethod(expenseDto.getPaymentMethod());
        expenseEntity.setIncome(expenseDto.getIncome());
        expenseEntity.setAmount(expenseDto.getAmount());
        expenseEntity.setDate(expenseDto.getDate());
        return expenseEntity;

    }

    public double getTotalAmountByDay(LocalDate date){
//        List<ExpenseEntity> expense = expenseRepo.findByDate(date);
//        double totalAmount = 0.0;
//        for(ExpenseEntity expenseEntity: expense){
//            if(!expenseEntity.isIncome()){
//                totalAmount+=expenseEntity.getAmount();
//            }
//        }
//        return totalAmount;
        return expenseRepo.findByDate(date).stream()
                .filter(expenseEntity -> !expenseEntity.isIncome())
                .mapToDouble(ExpenseEntity::getAmount)
                .sum();
    }
    public double getCurrentBalance(){
        List<ExpenseEntity> expense = expenseRepo.findAll();
        double totalExpense = 0.0;
        double totalSavings = 0.0;
        double currentBalance;
        for(ExpenseEntity expenseEntity: expense){
            if(!expenseEntity.isIncome()){
                totalExpense+=expenseEntity.getAmount();
            }
            else{
                totalSavings+=expenseEntity.getAmount();
            }
        }
        currentBalance = totalSavings - totalExpense;
        return currentBalance;
    }

    public double getMonthlyExpense(LocalDate startDate,LocalDate endDate){
//        List<ExpenseEntity> expense = expenseRepo.findByDateBetween(startDate,endDate);
//        double totalAmount = 0.0;
//        for(ExpenseEntity expenseEntity: expense){
//            if(!expenseEntity.isIncome()){
//                totalAmount+=expenseEntity.getAmount();
//            }
//        }
//        return totalAmount;
        return expenseRepo.findByDateBetween(startDate, endDate).stream()
                .filter(expenseEntity -> !expenseEntity.isIncome())
                .mapToDouble(ExpenseEntity::getAmount)
                .sum();
    }
    public double getExpenseByPaymentMethod(String paymentMethod){
//        List<ExpenseEntity> expense = expenseRepo.findByPaymentMethod(paymentMethod);
//        double totalAmount = 0.0;
//        for(ExpenseEntity expenseEntity: expense){
//            if(!expenseEntity.isIncome()){
//                totalAmount+=expenseEntity.getAmount();
//            }
//        }
//        return totalAmount;
        return expenseRepo.findByPaymentMethod(paymentMethod).stream()
                .filter(expenseEntity -> !expenseEntity.isIncome())
                .mapToDouble(ExpenseEntity::getAmount)
                .sum();

    }

}
