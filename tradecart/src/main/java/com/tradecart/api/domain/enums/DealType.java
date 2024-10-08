package com.tradecart.api.domain.enums;

public enum DealType {

    SELL(1, "Venda"),
    EXCHANGE(2, "Troca"),
    WISH(3, "Desejo");

    private final int value;
    private final String description;

    DealType(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static DealType fromValue(int value) {
        for (DealType type : DealType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid Type: " + value);
    }

}
