package com.bobocode.service;

import com.bobocode.entity.Advisor;
import com.bobocode.entity.Application;
import com.bobocode.exception.AdvisorNotFoundException;
import com.bobocode.reposiroty.AdvisorRepository;
import com.bobocode.reposiroty.ApplicationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditAdvisoryService {
    private final AdvisorRepository advisorRepository;
    private final ApplicationRepository applicationRepository;

    @Transactional
    public Optional<Application> assignApplication(Long advisorId) {
        Advisor advisor = advisorRepository.findById(advisorId)
                .orElseThrow(() -> new AdvisorNotFoundException("Cannot find an advisor by ID: " + advisorId));

        Pair<BigDecimal, BigDecimal> assignableAmountRange = advisor.getAssignableAmountRange();
        double min = assignableAmountRange.getLeft().doubleValue();
        double max = assignableAmountRange.getRight().doubleValue();

        Optional<Application> application = applicationRepository.findTheOldestNewApplicationWithAmountRange(min, max);
        application.ifPresent(advisor::assignApplication);
        return application;
    }
}
