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
    @OneToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="classroom_id", nullable = false)
    private Classroom classroomID;
    @OneToOne (optional=false, cascade=CascadeType.ALL) // спросить, может множество расписаний с одним предметом
    @JoinColumn (name="subject_id", nullable = false)
    private Subject subjectID;
    @OneToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="teacher_id", nullable = false)
    private Teacher teacherID;
    @Column(name = "week_day", nullable = false)
    private int weekDay;
    @Column(name = "date", nullable = false)
    private Date date;
    @OneToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="calendar_id", nullable = false)
    private Calendar calendarId;
    @Column(name = "hometask")
    private String hometask;
    @OneToOne (optional=false, mappedBy="lessonID")
    private Attendance attendance;
    @OneToOne (optional=false, mappedBy="lessonID")
    private AcademicPerfomance academicPerfomance;

    public Shedule(Classroom classroomID, Subject subjectID, Teacher teacherID, int weekDay, Date date, Calendar calendarId, String hometask) {
        this.classroomID = classroomID;
        this.subjectID = subjectID;
        this.teacherID = teacherID;
        this.weekDay = weekDay;
        this.date = date;
        this.calendarId = calendarId;
        this.hometask = hometask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shedule shedule = (Shedule) o;
        return Objects.equals(id, shedule.id) && Objects.equals(classroomID, shedule.classroomID) && Objects.equals(subjectID, shedule.subjectID) && Objects.equals(teacherID, shedule.teacherID) && Objects.equals(weekDay, shedule.weekDay) && Objects.equals(date, shedule.date) && Objects.equals(calendarId, shedule.calendarId) && Objects.equals(hometask, shedule.hometask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, classroomID, subjectID, teacherID, weekDay, date, calendarId, hometask);
    }
}
