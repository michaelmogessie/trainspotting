import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteScheduleConfirmDialogComponent } from './delete-schedule-confirm-dialog.component';

describe('DeleteScheduleConfirmDialogComponent', () => {
  let component: DeleteScheduleConfirmDialogComponent;
  let fixture: ComponentFixture<DeleteScheduleConfirmDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteScheduleConfirmDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteScheduleConfirmDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
