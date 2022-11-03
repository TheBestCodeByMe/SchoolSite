package com.example.schoolsite.entity;

import com.google.firebase.database.annotations.NotNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class Subject {
    @Id
    @GeneratedValue //(strategy = GenerationType.AUTO)
    @GraphQLQuery(name="id", description = "A subject`s id")
    private Long id;
    //@Column(name = "subjectName", nullable = false)
    @GraphQLQuery(name="subject_name", description = "A subject`s name")
    private @NotNull  String subjectName;
}
