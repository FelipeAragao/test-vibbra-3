package com.tradecart.api.domain.enums;

public enum UrgencyType {
    LOW(1, "Baixa"),
    MEDIUM(2, "Média"),
    HIGH(3, "Alta"),
    DATE(4, "Data");

    private final int value;
    private final String description;

    UrgencyType(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static UrgencyType fromValue(int value) {
        for (UrgencyType type : UrgencyType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid urgency type: " + value);
    }
}
