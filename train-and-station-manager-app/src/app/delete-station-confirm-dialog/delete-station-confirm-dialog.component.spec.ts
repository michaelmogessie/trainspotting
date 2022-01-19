import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteStationConfirmDialogComponent } from './delete-station-confirm-dialog.component';

describe('DeleteStationConfirmDialogComponent', () => {
  let component: DeleteStationConfirmDialogComponent;
  let fixture: ComponentFixture<DeleteStationConfirmDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteStationConfirmDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteStationConfirmDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
