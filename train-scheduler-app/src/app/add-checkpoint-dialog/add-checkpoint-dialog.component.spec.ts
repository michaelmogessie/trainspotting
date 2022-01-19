import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCheckpointDialogComponent } from './add-checkpoint-dialog.component';

describe('AddCheckpointDialogComponent', () => {
  let component: AddCheckpointDialogComponent;
  let fixture: ComponentFixture<AddCheckpointDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddCheckpointDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCheckpointDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
