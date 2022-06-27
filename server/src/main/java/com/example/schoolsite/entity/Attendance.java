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
@Table(name = "attendance")
@ToString
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="pupil_id", nullable = false)
    private Pupil pupilID;
    @OneToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="class_id", nullable = false)
    private Classroom classID;
    @OneToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="lesson_id", nullable = false)
    private Shedule lessonID;

    public Attendance(Pupil pupilID, Classroom classID, Shedule lessonID) {
        this.pupilID = pupilID;
        this.classID = classID;
        this.lessonID = lessonID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendance that = (Attendance) o;
        return Objects.equals(id, that.id) && Objects.equals(pupilID, that.pupilID) && Objects.equals(classID, that.classID) && Objects.equals(lessonID, that.lessonID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pupilID, classID, lessonID);
    }
}