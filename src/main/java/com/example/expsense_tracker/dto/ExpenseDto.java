package com.example.expsense_tracker.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDto {
    @NotNull
    private String description;

    @NotBlank(message = "")
    @Pattern(regexp = "^(GPAY|CASH|CARD|BANK)$", message = "payment must be cash,card,gpay etc")
    private String paymentMethod;

    @NotNull
    @Min(value = 1,message = "Amount must be 1 or greater ")
    private double amount;

    @NotNull(message = "Cant be null")
    private boolean income;

    public boolean getIncome() {
        return income;
    }
    @NotNull
    private LocalDate date;

//    public boolean getIncome() {
//    }


//    @NotNull
//    private String name;
//    @NotBlank(message = "type of transcations is mandatory")
//    private String typetranscations;
//    @Min(value = 1,message = "amount should be greater than 1")
//    private double amount;
//    @Size(min=5, max=300)
//    private String description;
}
