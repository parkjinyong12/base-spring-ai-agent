package com.example.agent.dto;

public record ChatOptionsRequest(
        String name,
        String provider,
        String apiKey,
        String model,
        Integer maxTokens,
        Double temperature
) {}
