package com.bobocode.entity;

import com.bobocode.exception.AdvisorHasAssignedApplicationException;
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
import org.apache.commons.lang3.tuple.Pair;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.bobocode.util.AdvisorAmountUtil.ASSOCIATE_MAX_AMOUNT_BOUND;
import static com.bobocode.util.AdvisorAmountUtil.ASSOCIATE_MIN_AMOUNT_BOUND;
import static com.bobocode.util.AdvisorAmountUtil.PARTNER_MAX_AMOUNT_BOUND;
import static com.bobocode.util.AdvisorAmountUtil.PARTNER_MIN_AMOUNT_BOUND;
import static com.bobocode.util.AdvisorAmountUtil.SENIOR_MAX_AMOUNT_BOUND;
import static com.bobocode.util.AdvisorAmountUtil.SENIOR_MIN_AMOUNT_BOUND;

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

    public void assignApplication(Application application) {
        verifyHasNoAssignedApplication();
        applications.add(application);
        application.assignAdvisor(this);
    }

    private void verifyHasNoAssignedApplication() {
        if (hasAssignedApplication()) {
           throw new AdvisorHasAssignedApplicationException("Advisor already was assigned to some application.");
        }
    }

    private boolean hasAssignedApplication() {
        return applications.stream()
                .anyMatch(application -> application.getStatus() == Application.ApplicationStatus.ASSIGNED);
    }

    public Pair<BigDecimal, BigDecimal> getAssignableAmountRange() {
        return switch (role) {
            case ASSOCIATE -> Pair.of(ASSOCIATE_MIN_AMOUNT_BOUND, ASSOCIATE_MAX_AMOUNT_BOUND);
            case PARTNER -> Pair.of(PARTNER_MIN_AMOUNT_BOUND, PARTNER_MAX_AMOUNT_BOUND);
            case SENIOR -> Pair.of(SENIOR_MIN_AMOUNT_BOUND, SENIOR_MAX_AMOUNT_BOUND);
        };
    }

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
