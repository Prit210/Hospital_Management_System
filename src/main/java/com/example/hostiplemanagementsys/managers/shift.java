package com.example.hostiplemanagementsys.managers;

import java.util.HashMap;
import java.util.Map;

public record shift() {
    public static Map<String, String> Pairs = new HashMap<>();

    static {
        Pairs.put("Language", "Java");
        Pairs.put("Framework", "Spring");
        Pairs.put("Model", "Java");
        Pairs.put("Model", "Spring");
    }
}
