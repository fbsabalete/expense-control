package com.fernando.expensecontrol.repository;

import com.fernando.expensecontrol.model.Expense;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {
    public List<Expense> findAllByDateBetween(Date dateStart, Date dateEnd);

    @Query("select e from Expense e where year(e.date) = ?1 and month(e.date) = ?2")
    List<Expense> getByYearAndMonth(Integer year, Integer month);
}
