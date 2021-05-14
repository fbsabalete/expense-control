package com.fernando.expensecontrol.controller;

import com.fernando.expensecontrol.model.Expense;
import com.fernando.expensecontrol.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/expense")
@CrossOrigin("*")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Expense> save(@RequestBody @Valid Expense expense){
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseService.save(expense));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> update(@PathVariable long id, @RequestBody Expense expense){
        return ResponseEntity.ok(expenseService.update(id, expense));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        expenseService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Expense>> findAll(){
        return ResponseEntity.ok(expenseService.findAll());
    }

    @GetMapping("/params")
    public ResponseEntity<List<Expense>> findByDateBetween (@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateStart,
                                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateEnd){
        return ResponseEntity.ok(expenseService.findByDateBetween(dateStart, dateEnd));
    }

    @GetMapping("/currentMonth")
    public ResponseEntity<List<Expense>> findAllCurrentMonth(){
        return ResponseEntity.ok(expenseService.findAllCurrentMonth());
    }

    @GetMapping("/month")
    public ResponseEntity<List<Expense>> findAllByMonth(@RequestParam int year, @RequestParam int month){
        return ResponseEntity.ok(expenseService.findAllByMonthOfYear(year, month));
    }

}
