import { TestBed } from '@angular/core/testing';

import { UserService } from '../users/user.service';
import {PupilService} from "./pupil.service";

describe('PupilService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PupilService = TestBed.get(PupilService);
    expect(service).toBeTruthy();
  });
});
