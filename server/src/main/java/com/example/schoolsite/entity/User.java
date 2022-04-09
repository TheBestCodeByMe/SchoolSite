package com.example.schoolsite.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users",
@UniqueConstraint(columnNames = "login",
@UniqueConstraint(columnNames = "email")))
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role", nullable = false)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "link", nullable = true)
    private String link;

    public User(String login, String password, String status, String link) {
        super();
        this.login = login;
        this.password = password;
        this.status = status;
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles) && Objects.equals(status, user.status) && Objects.equals(link, user.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, roles, status, link);
    }
}
