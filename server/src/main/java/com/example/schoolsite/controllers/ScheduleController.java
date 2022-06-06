package com.example.schoolsite.controllers;

import com.example.schoolsite.dto.SheduleDTO;
import com.example.schoolsite.exception.ResourceNotFoundException;
import com.example.schoolsite.services.SheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ScheduleController {

    private final SheduleService sheduleService;

    @GetMapping("/getScheduleDTOPupil/{userId}/{date}")
    public ResponseEntity<List<SheduleDTO>> getScheduleByIdAndDate(@PathVariable(value = "userId") Long userId, @PathVariable(value = "date") String date)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(sheduleService.getScheduleByIdAndDate(userId, date));
    }
}
