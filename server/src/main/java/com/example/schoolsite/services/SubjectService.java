package com.example.schoolsite.services;

import com.example.schoolsite.entity.Subject;
import com.example.schoolsite.workWithDatabase.repo.SubjectRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@GraphQLApi
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository){
        this.subjectRepository = subjectRepository;
    }

    @GraphQLQuery(name="subjects")
    public List<Subject> getSubjetcs(){
        return subjectRepository.findAll();
    }

    @GraphQLQuery(name="subject")
    public Optional<Subject> getSubjectById(@GraphQLArgument(name="id") Long id){
        return subjectRepository.findById(id);
    }

    @GraphQLMutation(name="saveSubject")
    public Subject saveSubject(@GraphQLArgument(name="subject") Subject subject){
        return subjectRepository.save(subject);
    }

    @GraphQLMutation(name="deleteSubject")
    public void deleteSubject(@GraphQLArgument(name="id") Long id){
        subjectRepository.deleteById(id);
    }
}
