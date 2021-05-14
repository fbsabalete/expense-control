import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Expense } from '../model/Expense';

@Injectable({
  providedIn: 'root'
})
export class ExpenseService {

  baseUrl: string = environment.url + "/expense"

  constructor(private http: HttpClient) { }

  getAllExpenses(): Observable<Expense[]>{
    return this.http.get<Expense[]>(this.baseUrl);
  }

  getExpenseCurrentMonth(): Observable<Expense[]>{
    return this.http.get<Expense[]>(this.baseUrl + "/currentMonth")
  }

  postExpense(expense: Expense): Observable<Expense>{
    return this.http.post<Expense>(this.baseUrl, expense)
  }
}
