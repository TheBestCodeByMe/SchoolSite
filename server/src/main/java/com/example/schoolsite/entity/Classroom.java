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
@Table(name = "classroom")
@ToString
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="classroom_teacher_id", nullable = false)
    private Teacher classroomTeacherId;
    @Column(name = "name", nullable = false)
    private String name;
    @OneToOne (optional=false, mappedBy="classroomId")
    private Pupil pupil;
    @OneToOne (optional=false, mappedBy="classroomID")
    private Shedule shedule;
    @OneToOne (optional=false, mappedBy="classID")
    private Attendance attendance;
    @OneToOne (optional=false, mappedBy="classID")
    private AcademicPerfomance academicPerfomance;

    public Classroom(Teacher classroomTeacherId, String name) {
        this.classroomTeacherId = classroomTeacherId;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classroom classroom = (Classroom) o;
        return Objects.equals(id, classroom.id) && Objects.equals(classroomTeacherId, classroom.classroomTeacherId) && Objects.equals(name, classroom.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, classroomTeacherId, name);
    }
}