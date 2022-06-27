package com.example.schoolsite.services;

import com.example.schoolsite.dto.SheduleDTO;
import com.example.schoolsite.exception.ResourceNotFoundException;

import java.util.List;

public interface SheduleService {

    List<SheduleDTO> getScheduleByIdAndDate(Long userId, String date)
            throws ResourceNotFoundException;

    List<SheduleDTO> getScheduleDTOByIdAndDate(Long userId, String date);
}
