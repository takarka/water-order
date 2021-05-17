import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FuseProgressBarService } from '@fuse/components/progress-bar/progress-bar.service';
import { DialogService } from '@ms/core/components/dialog/service/dialog.service';
import { OrderTimeRangeService } from 'app/main/service/order-time-range.service';
import { OrderTimeRange } from 'app/main/shared/model/time-range.model';
import * as moment from 'moment';

@Component({
  selector: 'time-range-dialog',
  templateUrl: './time-range-dialog.component.html',
  styleUrls: ['./time-range-dialog.component.css'],
})
export class TimeRangeDialogComponent implements OnInit {
  form: FormGroup;
  orderTimeRange: OrderTimeRange;
  title: string;

  constructor(
    private fb: FormBuilder,
    private dialogService: DialogService,
    public dialogRef: MatDialogRef<TimeRangeDialogComponent>,
    private service: OrderTimeRangeService,
    private progressService: FuseProgressBarService,
    @Inject(MAT_DIALOG_DATA) private data: any
  ) {
    this.orderTimeRange = data && data.orderTimeRange ? data.orderTimeRange : new OrderTimeRange();
    if (this.orderTimeRange.id) {
      this.title = 'Изиенить временной интервал';
    } else {
      this.title = 'Добавить временной интервал';
    }
    this.form = this.createCityForm();
    this.form.patchValue(this.orderTimeRange);
  }

  ngOnInit() {

  }

  createCityForm(): FormGroup {
    return this.fb.group({
      id: [null],
      start: [null],
      end: [null],
      order: [0]
    });
  }

  save() {
    const orderTimeRange = this.form.value;
    const start = moment(orderTimeRange.start, "hh:mm");
    const end = moment(orderTimeRange.end, "hh:mm");
    if(!start.isBefore(end)) {
      this.dialogService.error("Время начала должно быть раньше окончания");
      return;
    }
    if (orderTimeRange.id || orderTimeRange.id !== null) {
      this.progressService.show();
      this.service.update(orderTimeRange).subscribe(res => {
        this.progressService.hide();
        this.orderTimeRange = res.body;
        this.form.patchValue(orderTimeRange);
        this.dialogService.success('Временной интервал успешно изменен');
        this.dialogRef.close({ action: 'save' });
      });
    } else {
      this.progressService.show();
      this.service.save(orderTimeRange).subscribe(res => {
        this.progressService.hide();
        this.orderTimeRange = res.body;
        this.form.patchValue(orderTimeRange);
        this.dialogService.success('Временной интервал успешно создан');
        this.dialogRef.close({ action: 'save' });
      });
    }
  }

  close() {
    console.log("close");
    this.dialogRef.close();
  }

}
