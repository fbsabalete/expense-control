package com.fernando.expensecontrol.service;


import com.fernando.expensecontrol.model.Income;
import com.fernando.expensecontrol.repository.IncomeRepository;
import com.fernando.expensecontrol.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository repository;

    private Income findById(long id){
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object id="+id+"not found"));
    }

    public Income save(Income income){
        if(income.getDate() == null){
            income.setDate(new Date());
        }
        return repository.save(income);
    }

    public Income update(long id, Income expense){
        findById(id);
        expense.setId(id);
        return repository.save(expense);
    }

    public void delete(long id){
        findById(id);
        repository.deleteById(id);
    }

    public List<Income> findByDateBetween(Date dateStart, Date dateEnd){
        return repository.findAllByDateBetween(dateStart, dateEnd);
    }

    public List<Income> findAllCurrentMonth(){
        LocalDate today = LocalDate.now();
        return repository.getByYearAndMonth(today.getYear(), today.getMonthValue());
    }

    public List<Income> findAllByMonthOfYear(int year, int month){
        return repository.getByYearAndMonth(year, month);
    }

    public List<Income> findAll(){
        List<Income> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

}
