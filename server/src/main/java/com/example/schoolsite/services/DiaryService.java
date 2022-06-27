package com.example.schoolsite.services;

import com.example.schoolsite.dto.DiaryDTO;
import com.example.schoolsite.entity.*;
import com.example.schoolsite.map.Mapper;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface DiaryService {
    DiaryDTO addAcademicPerfomance(DiaryDTO diaryDTO);

    DiaryDTO addAttendance(DiaryDTO diaryDTO);

    DiaryDTO addSubject(DiaryDTO diaryDTO);

    boolean getAttendance(DiaryDTO diaryDTO);

    boolean getAcademicPerfomance(DiaryDTO diaryDTO);

    List<DiaryDTO> getDiaryDTOByUser(Long id);

    int getNumbAttendance(Long id);

    List<DiaryDTO> getDiaryDTOByClass(String classForSearch);

    double getAverageGrade(Long id);

    void saveGradesByUserId(Long userId);
}
