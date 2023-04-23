package com.bobocode.util;

import java.math.BigDecimal;

public final class AdvisorAmountUtil {
    public static final BigDecimal ASSOCIATE_MIN_AMOUNT_BOUND = BigDecimal.ZERO;
    public static final BigDecimal ASSOCIATE_MAX_AMOUNT_BOUND = BigDecimal.valueOf(10_000);
    public static final BigDecimal PARTNER_MIN_AMOUNT_BOUND = BigDecimal.valueOf(10_000);
    public static final BigDecimal SENIOR_MIN_AMOUNT_BOUND = BigDecimal.valueOf(50_000);
    public static final BigDecimal PARTNER_MAX_AMOUNT_BOUND = BigDecimal.valueOf(50_000);
    public static final BigDecimal SENIOR_MAX_AMOUNT_BOUND = BigDecimal.valueOf(Integer.MAX_VALUE);

    private AdvisorAmountUtil() {
    }
}
