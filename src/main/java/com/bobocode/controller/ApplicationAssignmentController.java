package com.bobocode.controller;

import com.bobocode.dto.converter.ApplicationDtoConverter;
import com.bobocode.service.CreditAdvisoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApplicationAssignmentController {

    private final CreditAdvisoryService creditAdvisoryService;

    @PostMapping("/assign-application/{advisorId}")
    public ResponseEntity<?> assignApplication(@PathVariable Long advisorId) {
        return creditAdvisoryService.assignApplication(advisorId)
                .map(ApplicationDtoConverter::convertToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

}
