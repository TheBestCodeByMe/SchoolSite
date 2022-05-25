package com.example.schoolsite.services;

import com.example.schoolsite.dto.PupilDTO;

public interface PupilService {
    PupilDTO getPupilByFIO(String userId);
}
