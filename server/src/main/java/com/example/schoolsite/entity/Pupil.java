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
@Table(name = "pupil")
@ToString
public class Pupil {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="user_id")
    private User userId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "lastname", nullable = false)
    private String lastname;
    @Column(name = "patronymic", nullable = false)
    private String patronymic;
    @Column(name = "date_of_birthday")
    private Date dateOfBirthday;
    @Column(name = "email")
    private String email;
    @Column(name = "personal_check", nullable = false)
    private String personalCheck;
    @OneToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="classroom_id", nullable = false)
    private Classroom classroomId;
    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="parents_id", nullable = false)
    private Parents parentsId;
    @OneToOne (optional=false, mappedBy="pupilID")
    private Attendance attendance;
    @OneToOne (optional=false, mappedBy="pupilID")
    private AcademicPerfomance academicPerfomance;

    public Pupil(User userId, String name, String lastname, String patronymic, Date dateOfBirthday, String email, String personalCheck, Classroom classroomId, Parents parentsId) {
        this.userId = userId;
        this.name = name;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.dateOfBirthday = dateOfBirthday;
        this.email = email;
        this.personalCheck = personalCheck;
        this.classroomId = classroomId;
        this.parentsId = parentsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pupil pupil = (Pupil) o;
        return Objects.equals(id, pupil.id) && Objects.equals(userId, pupil.userId) && Objects.equals(name, pupil.name) && Objects.equals(lastname, pupil.lastname) && Objects.equals(patronymic, pupil.patronymic) && Objects.equals(dateOfBirthday, pupil.dateOfBirthday) && Objects.equals(email, pupil.email) && Objects.equals(personalCheck, pupil.personalCheck) && Objects.equals(classroomId, pupil.classroomId) && Objects.equals(parentsId, pupil.parentsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, lastname, patronymic, dateOfBirthday, email, personalCheck, classroomId, parentsId);
    }
}