package com.fernando.expensecontrol.repository;


import com.fernando.expensecontrol.model.Income;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface IncomeRepository extends CrudRepository<Income, Long> {
    public List<Income> findAllByDateBetween(Date dateStart, Date dateEnd);

    @Query("select e from Income e where year(e.date) = ?1 and month(e.date) = ?2")
    List<Income> getByYearAndMonth(Integer year, Integer month);
}
