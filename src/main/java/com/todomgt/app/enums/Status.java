package com.todomgt.app.enums;

import java.util.HashMap;
import java.util.Map;

public enum Status {

    ACTIVE,
    DONE
    ;

    private static Map<String, Status> REVERSE_MAP = new HashMap<>();
    static {
        for (Status status : Status.values()) {
            REVERSE_MAP.put(status.name(), status);
        }
    }

    public static Status getStatus(String statusStr) {
        return REVERSE_MAP.get(statusStr);
    }
}
