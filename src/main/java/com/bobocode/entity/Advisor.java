package com.bobocode.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "advisors")
public class Advisor extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AdvisorRole role;

    @OneToMany(mappedBy = "advisor")
    @ToString.Exclude
    @Setter(AccessLevel.PRIVATE)
    private List<Application> applications = new ArrayList<>();

    public enum AdvisorRole {
        ASSOCIATE, PARTNER, SENIOR
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Advisor advisor = (Advisor) o;
        return getId() != null && Objects.equals(getId(), advisor.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
