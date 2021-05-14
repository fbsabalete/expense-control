package com.fernando.expensecontrol.service;

import com.fernando.expensecontrol.model.Expense;
import com.fernando.expensecontrol.repository.ExpenseRepository;
import com.fernando.expensecontrol.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository repository;

    private Expense findById(long id){
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object id="+id+"not found"));
    }

    public Expense save(Expense expense){
        if(expense.getDate() == null){
            expense.setDate(new Date());
        }
        return repository.save(expense);
    }

    public Expense update(long id, Expense expense){
        findById(id);
        expense.setId(id);
        return repository.save(expense);
    }

    public void delete(long id){
        findById(id);
        repository.deleteById(id);
    }

    public List<Expense> findByDateBetween(Date dateStart, Date dateEnd){
        return repository.findAllByDateBetween(dateStart, dateEnd);
    }

    public List<Expense> findAllCurrentMonth(){
        LocalDate today = LocalDate.now();
        return repository.getByYearAndMonth(today.getYear(), today.getMonthValue());
    }

    public List<Expense> findAllByMonthOfYear(int year, int month){
        return repository.getByYearAndMonth(year, month);
    }

    public List<Expense> findAll(){
        List<Expense> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

}
