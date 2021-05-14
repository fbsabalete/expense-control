package com.fernando.expensecontrol.controller;

import com.fernando.expensecontrol.model.Expense;
import com.fernando.expensecontrol.model.Income;
import com.fernando.expensecontrol.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/income")
@CrossOrigin("*")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;


    @PostMapping
    public ResponseEntity<Income> save(@RequestBody @Valid Income income){
        return ResponseEntity.status(HttpStatus.CREATED).body(incomeService.save(income));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Income> update(@PathVariable long id, @RequestBody Income income){
        return ResponseEntity.ok(incomeService.update(id, income));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        incomeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Income>> findAll(){
        return ResponseEntity.ok(incomeService.findAll());
    }

    @GetMapping("/params")
    public ResponseEntity<List<Income>> findByDateBetween (@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateStart,
                                                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateEnd){
        return ResponseEntity.ok(incomeService.findByDateBetween(dateStart, dateEnd));
    }

    @GetMapping("/currentMonth")
    public ResponseEntity<List<Income>> findAllCurrentMonth(){
        return ResponseEntity.ok(incomeService.findAllCurrentMonth());
    }

    @GetMapping("/month")
    public ResponseEntity<List<Income>> findAllByMonth(@RequestParam int year, @RequestParam int month){
        return ResponseEntity.ok(incomeService.findAllByMonthOfYear(year, month));
    }

}
