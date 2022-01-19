import { TestBed } from '@angular/core/testing';

import { DepartureUpdateService } from './departure-update.service';

describe('DepartureUpdateService', () => {
  let service: DepartureUpdateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DepartureUpdateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
