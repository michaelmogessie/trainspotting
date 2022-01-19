import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteTrainConfirmDialogComponent } from './delete-train-confirm-dialog.component';

describe('DeleteTrainConfirmDialogComponent', () => {
  let component: DeleteTrainConfirmDialogComponent;
  let fixture: ComponentFixture<DeleteTrainConfirmDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteTrainConfirmDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteTrainConfirmDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
