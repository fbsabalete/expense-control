import { Component, OnInit } from '@angular/core';
import { DatepickerOptions } from 'ngx-dates-picker';
import { Expense } from '../model/Expense';
import { ExpenseService } from '../service/expense.service';


@Component({
  selector: 'app-main-view',
  templateUrl: './main-view.component.html',
  styleUrls: ['./main-view.component.css']
})
export class MainViewComponent implements OnInit {

  

  monthExpenseList: Expense[]
  expenseList: Expense[]

  newExpense: Expense = new Expense()
  date: Date = new Date()
  options: DatepickerOptions = {
    addClass: {"bg-dark": true}
  }

  constructor(
    private expenseService: ExpenseService
  ) { }

  ngOnInit(){
    this.findExpenseCurrentMonth()
    
  }


  findAllExpense(){
    this.expenseService.getAllExpenses().subscribe((resp: Expense[]) => {
      this.expenseList = resp
    })
  }

  findExpenseCurrentMonth(){
    this.expenseService.getExpenseCurrentMonth().subscribe((resp: Expense[]) => {
      this.monthExpenseList = resp
    })
  }

  findExpenseBetween()

  saveExpense(){
    this.date.setHours(2)
    this.newExpense.date = this.date.toISOString()
    this.expenseService.postExpense(this.newExpense).subscribe((resp)=>{
      this.newExpense = new Expense()
      this.date = new Date()
      this.findExpenseCurrentMonth()
      this.findAllExpense()
    })
  }

  filter(event){

  }

}
