import { TestBed } from '@angular/core/testing';

import { TabchangeService } from './tabchange.service';

describe('TabchangeService', () => {
  let service: TabchangeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TabchangeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
