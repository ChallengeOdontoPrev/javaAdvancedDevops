package com.fiap.challengeOdontoPrev.enums;

public enum ClassDetected {
    SORRISO_NORMAL("SORRISO_NORMAL"),
    APARELHO("APARELHO"),
    CARIES("CARIES");

    private final String role;

    ClassDetected(String role) {
        this.role = role;
    }
}
