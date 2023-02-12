package com.brau.employeemanagerservice.domain.enums;

import java.math.BigDecimal;

public enum RemunerationRange {
    FAIXA_1(BigDecimal.valueOf(400.00), 0.15),
    FAIXA_2(BigDecimal.valueOf(800.00), 0.12),
    FAIXA_3(BigDecimal.valueOf(1200.00), 0.10),
    FAIXA_4(BigDecimal.valueOf(2000.00), 0.07),
    FAIXA_5(BigDecimal.valueOf(1000000.00), 0.04);

    private final BigDecimal maximumRemuneration;
    private final double percentual;

    RemunerationRange(BigDecimal maximumRemuneration, double percentual) {
        this.maximumRemuneration = maximumRemuneration;
        this.percentual = percentual;
    }

    public static RemunerationRange getPorSalario(BigDecimal remuneration) {
        for (RemunerationRange remunerationRange : values()) {
            if (remuneration.compareTo(remunerationRange.maximumRemuneration) <= 0) {
                return remunerationRange;
            }
        }
        return null;
    }

    public BigDecimal getMaximumRemuneration() {
        return maximumRemuneration;
    }

    public double getPercentual() {
        return percentual;
    }

}