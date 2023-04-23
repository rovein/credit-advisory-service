package com.bobocode.reposiroty;

import com.bobocode.entity.Application;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface ApplicationRepository extends CrudRepository<Application, Long> {

    @Query("select a from Application a where a.status = 'NEW' and a.amount >= ?1 and a.amount <= ?2")
    Optional<Application> findTheOldestNewApplicationWithAmountRange(BigDecimal min, BigDecimal max);

}
