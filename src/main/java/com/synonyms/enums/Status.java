package com.synonyms.enums;

public enum Status {
    ACTIVE,
    INACTIVE,
    BLOCKED,
    LOCKED,
    ;

    public static Status getValidStatus(String status) {
        for(Status s : Status.values()) {
            if (s.name().equals(status))
                return s;
        }
        return null;
    }
}