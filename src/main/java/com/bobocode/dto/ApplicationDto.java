package com.bobocode.dto;

import com.bobocode.entity.Application;

import java.time.LocalDateTime;

public record ApplicationDto(
        Long id,
        double amount,
        Application.ApplicationStatus status,
        LocalDateTime createdAt,
        LocalDateTime assignedAt,
        Long applicantId,
        Long advisorId
) {
}
