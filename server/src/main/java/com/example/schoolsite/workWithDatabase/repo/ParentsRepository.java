package com.example.schoolsite.workWithDatabase.repo;

import com.example.schoolsite.entity.Parents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentsRepository extends JpaRepository<Parents, Long> {
    Parents findByNameDadAndLastnameDadAndPatronymicDadAndNameMomAndLastnameMomAndPatronymicMom(String nameDad, String lastnameDad, String patronymicDad, String nameMom, String lastnameMom, String patronymicMom);
}