package com.fiap.challengeOdontoPrev.enums;

public enum AppointmentStatus {
    SCHEDULED("SCHEDULED"),
    FINISHED("FINISHED");

    private final String code;

    AppointmentStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

