import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { NgxDatesPickerModule } from 'ngx-dates-picker';

import { AppComponent } from './app.component';
import { MonthPickerComponent } from './month-picker/month-picker.component';
import { MainViewComponent } from './main-view/main-view.component';


@NgModule({
  declarations: [
    AppComponent,
    MonthPickerComponent,
    MainViewComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgxDatesPickerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
