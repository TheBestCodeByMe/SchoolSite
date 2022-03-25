package com.example.schoolsite.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "calendar")
@ToString
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "SemesterID", nullable = false)
    private Long semesterID;
    @Column(name = "WeekDay", nullable = false)
    private int weekDay;
    @Column(name = "WeekDayName", nullable = false)
    private String weekDayName;
    @Column(name = "LessonTime", nullable = false)
    private String lessonTime;

    public Calendar(Long semesterID, int weekDay, String weekDayName, String lessonTime) {
        this.semesterID = semesterID;
        this.weekDay = weekDay;
        this.weekDayName = weekDayName;
        this.lessonTime = lessonTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calendar calendar = (Calendar) o;
        return weekDay == calendar.weekDay && Objects.equals(id, calendar.id) && Objects.equals(semesterID, calendar.semesterID) && Objects.equals(weekDayName, calendar.weekDayName) && Objects.equals(lessonTime, calendar.lessonTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, semesterID, weekDay, weekDayName, lessonTime);
    }
}