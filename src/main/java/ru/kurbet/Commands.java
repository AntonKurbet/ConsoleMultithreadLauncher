package ru.kurbet;

import java.util.HashMap;
import java.util.Map;

public enum Commands {
    START("start"), STOP("stop"), EXIT("exit"), NONE("none");

    private static final Map<String, Commands> map = new HashMap<>();

    private final String text;

    static {
        for (var command : Commands.values()) {
            map.put(command.text, command);
        }
    }

    Commands(String text) {
        this.text = text;
    }

    static Commands of(String text) {
        return map.getOrDefault(text, NONE);
    }
}
