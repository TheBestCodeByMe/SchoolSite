import { TestBed } from '@angular/core/testing';
import {SheduleService} from "./sheduleDTO.service";

describe('SheduleService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SheduleService = TestBed.get(SheduleService);
    expect(service).toBeTruthy();
  });
});
