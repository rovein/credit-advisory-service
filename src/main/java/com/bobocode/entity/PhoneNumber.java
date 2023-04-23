package com.bobocode.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Embeddable
@Table(name = "phone_numbers")
public class PhoneNumber {
    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NumberType type;

    public enum NumberType {
        HOME, WORK, MOBILE
    }
}
