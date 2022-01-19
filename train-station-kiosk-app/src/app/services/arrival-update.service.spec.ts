import { TestBed } from '@angular/core/testing';

import { ArrivalUpdateService } from './arrival-update.service';

describe('ArrivalUpdateService', () => {
  let service: ArrivalUpdateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ArrivalUpdateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
