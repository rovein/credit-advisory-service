package com.bobocode.dto.converter;

import com.bobocode.dto.ApplicationDto;
import com.bobocode.entity.Application;

public final class ApplicationDtoConverter {


    private ApplicationDtoConverter() {
    }

    public static ApplicationDto convertToDto(Application application) {
        return new ApplicationDto(
                application.getId(),
                application.getAmount(),
                application.getStatus(),
                application.getCreatedAt(),
                application.getAssignedAt(),
                application.getApplicant().getId(),
                application.getAdvisor().getId()
        );
    }
}
