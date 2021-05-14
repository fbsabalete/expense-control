import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Month } from '../model/Month';

@Component({
  selector: 'app-month-picker',
  templateUrl: './month-picker.component.html',
  styleUrls: ['./month-picker.component.css']
})
export class MonthPickerComponent implements OnInit {

  @Output() monthRangeSelected = new EventEmitter<any>();

  currentYearIndex: number;
  years: Array<number>;
  months: Array<Month> = new Array(12);
  monthsData: Array<{
    monthName: string,
    monthValue: number,
    monthYear: number,
    isInRange: boolean,
    isLowerEdge: boolean,
    isUpperEdge: boolean
  }>;
  rangeIndexes: Array<number>;
  monthViewSlicesIndexes: Array<number>;
  monthDataSlice: Array<{
    monthName: string,
    monthYear: number,
    isInRange: boolean,
    isLowerEdge: boolean,
    isUpperEdge: boolean
  }>;
  globalIndexOffset: number;

  onClick(indexClicked) {
    if (this.rangeIndexes[0] === null) {
      this.rangeIndexes[0] = this.globalIndexOffset+indexClicked;
    } else
    if (this.rangeIndexes[1] === null) {
      this.rangeIndexes[1] = this.globalIndexOffset+indexClicked;
      this.rangeIndexes.sort((a,b) => a-b);
      this.monthsData.forEach((month, index) => {
        if ((this.rangeIndexes[0] <= index) && (index <= this.rangeIndexes[1])) {
          month.isInRange = true;
        };
        if (this.rangeIndexes[0] === index) {
          month.isLowerEdge = true;
        };
        if (this.rangeIndexes[1] === index) {
          month.isUpperEdge = true;
        };
      })
      let fromMonthYear = this.monthsData[this.rangeIndexes[0]];
      let toMonthYear = this.monthsData[this.rangeIndexes[1]];
      let fromString = fromMonthYear.monthYear + "-" + fromMonthYear.monthValue + "-" + "01"
      let toString = toMonthYear.monthYear + "-"+toMonthYear.monthValue+"-"+"30"
      let values = [fromMonthYear, toMonthYear]
      this.emitData(values)
    } else {
      this.initRangeIndexes();
      this.initMonthsData();
      this.onClick(indexClicked);
      this.sliceDataIntoView();
    };
  };

  emitData(n : any) {
    this.monthRangeSelected.emit(n)
  };

  sliceDataIntoView() {
    this.globalIndexOffset = this.monthViewSlicesIndexes[this.currentYearIndex];
    this.monthDataSlice = this.monthsData.slice(this.globalIndexOffset,this.globalIndexOffset+24);
  };

  incrementYear() {
    if (this.currentYearIndex !== this.years.length-1) {
      this.currentYearIndex++
      this.sliceDataIntoView()
    };
  };

  decrementYear() {
    if (this.currentYearIndex !==0) {
      this.currentYearIndex--;
      this.sliceDataIntoView()
    };
  };

  initRangeIndexes() {
    this.rangeIndexes = [ null, null ];
  };

  initMonthsData() {
    this.monthsData = new Array();
    this.years.forEach( year => {
      this.months.forEach( month => {
        this.monthsData.push({
          monthName: month.monthName,
          monthValue: month.value,
          monthYear: year,
          isInRange: false,
          isLowerEdge: false,
          isUpperEdge: false
        })
      })
    })
  };

  initYearLabels() {
    const currentYear = (new Date()).getFullYear();
    const range = (start, stop, step) => Array.from({ length: (stop - start) / step + 1}, (_, i) => start + (i * step));
    this.years = range(currentYear-1, currentYear + 5, 1)
  };

  initMonthLabels() {
    for(let i = 0; i<12; i++){
      this.months[i] = new Month();
      this.months[i].monthName = new Date(`${i + 1}/1`).toLocaleDateString(undefined, {
        month: 'short'
      });
      this.months[i].value = i+1;
    }
  };

  initViewSlices() {
    this.monthViewSlicesIndexes = [];
    this.years.forEach((year,index) => {
      if (index===0) { this.monthViewSlicesIndexes.push(0) } else
      if (index===1) { this.monthViewSlicesIndexes.push(6) } else
      this.monthViewSlicesIndexes.push(this.monthViewSlicesIndexes[index-1]+12);
    })
  };

  ngOnInit() {
    this.initYearLabels();
    this.initMonthLabels();
    this.initViewSlices();
    this.initMonthsData();
    this.initRangeIndexes();
    this.currentYearIndex = this.years.findIndex(year => year === (new Date()).getFullYear());
    this.sliceDataIntoView();
  };

}
