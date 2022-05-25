package com.example.schoolsite.services;

import com.example.schoolsite.dto.PupilDTO;
import com.example.schoolsite.entity.Classroom;
import com.example.schoolsite.entity.Parents;
import com.example.schoolsite.entity.Pupil;
import com.example.schoolsite.map.Mapper;
import com.example.schoolsite.workWithDatabase.repo.ClassroomRepository;
import com.example.schoolsite.workWithDatabase.repo.ParentsRepository;
import com.example.schoolsite.workWithDatabase.repo.PupilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PupilServiceImpl implements PupilService {

    private final PupilRepository pupilRepository;

    private final ParentsRepository parentsRepository;

    private final ClassroomRepository classroomRepository;

    @Override
    public PupilDTO getPupilByFIO(String userId) {
        Pupil pupil = pupilRepository.findByUserId(Long.parseLong(userId));
        Parents parents = parentsRepository.getById(pupil.getParentsId());
        Classroom classroom = classroomRepository.getById(pupil.getClassroomId());
        return Mapper.mapToPupilDTO(pupil, parents, classroom);
    }
}
