package com.example.schoolsite.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "shedule")
@ToString
public class Shedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "classroomID", nullable = false)
    private Long classroomID; // TODO: сделать внешним ключом
    @Column(name = "subjectID", nullable = false)
    private Long subjectID; // TODO: сделать внешним ключом
    @Column(name = "teacherID", nullable = false)
    private Long teacherID; // TODO: сделать внешним ключом
    @Column(name = "weekDay", nullable = false)
    private Long weekDay;
    @Column(name = "LessonID", nullable = false)
    private String lessonID;
    @Column(name = "date", nullable = false)
    private Date date;
    @Column(name = "semestrID", nullable = false)
    private Long semestrID;
    @Column(name = "hometask", nullable = false)
    private String hometask;

    public Shedule(Long classroomID, Long subjectID, Long teacherID, Long weekDay, String lessonID, Date date, Long semestrID, String hometask) {
        this.classroomID = classroomID;
        this.subjectID = subjectID;
        this.teacherID = teacherID;
        this.weekDay = weekDay;
        this.lessonID = lessonID;
        this.date = date;
        this.semestrID = semestrID;
        this.hometask = hometask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shedule shedule = (Shedule) o;
        return Objects.equals(id, shedule.id) && Objects.equals(classroomID, shedule.classroomID) && Objects.equals(subjectID, shedule.subjectID) && Objects.equals(teacherID, shedule.teacherID) && Objects.equals(weekDay, shedule.weekDay) && Objects.equals(lessonID, shedule.lessonID) && Objects.equals(date, shedule.date) && Objects.equals(semestrID, shedule.semestrID) && Objects.equals(hometask, shedule.hometask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, classroomID, subjectID, teacherID, weekDay, lessonID, date, semestrID, hometask);
    }
}
