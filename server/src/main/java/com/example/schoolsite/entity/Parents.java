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
@Table(name = "parents")
@ToString
public class Parents {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "nameMom", nullable = false)
    private String nameMom;
    @Column(name = "lastnameMom", nullable = false)
    private String lastnameMom;
    @Column(name = "patronymicMom", nullable = false)
    private String patronymicMom;
    @Column(name = "nameDad", nullable = false)
    private String nameDad;
    @Column(name = "lastnameDad", nullable = false)
    private String lastnameDad;
    @Column(name = "patronymicDad", nullable = false)
    private String patronymicDad;
    @OneToMany (mappedBy="parentsId", fetch=FetchType.EAGER)
    private Pupil pupils;

    public Parents(String nameMom, String lastnameMom, String patronymicMom, String nameDad, String lastnameDad, String patronymicDad) {
        this.nameMom = nameMom;
        this.lastnameMom = lastnameMom;
        this.patronymicMom = patronymicMom;
        this.nameDad = nameDad;
        this.lastnameDad = lastnameDad;
        this.patronymicDad = patronymicDad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parents parents = (Parents) o;
        return Objects.equals(id, parents.id) && Objects.equals(nameMom, parents.nameMom) && Objects.equals(lastnameMom, parents.lastnameMom) && Objects.equals(patronymicMom, parents.patronymicMom) && Objects.equals(nameDad, parents.nameDad) && Objects.equals(lastnameDad, parents.lastnameDad) && Objects.equals(patronymicDad, parents.patronymicDad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameMom, lastnameMom, patronymicMom, nameDad, lastnameDad, patronymicDad);
    }
}
