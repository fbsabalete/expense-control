package com.fernando.expensecontrol.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
public class Income {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Positive @NotNull
    private double amount;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @NotEmpty
    private String origin;

    public Income(long id, double amount, Date date, String origin) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.origin = origin;
    }

    public Income() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
