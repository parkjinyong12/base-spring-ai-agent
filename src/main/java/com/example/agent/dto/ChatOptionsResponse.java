package com.example.agent.dto;

import com.example.agent.domain.AiChatOptions;

public record ChatOptionsResponse(
        Long id,
        String name,
        String provider,
        String model,
        Integer maxTokens,
        Double temperature,
        boolean defaultFlag
) {
    public static ChatOptionsResponse from(AiChatOptions entity) {
        return new ChatOptionsResponse(
                entity.getId(),
                entity.getName(),
                entity.getProvider(),
                entity.getModel(),
                entity.getMaxTokens(),
                entity.getTemperature(),
                entity.isDefaultFlag()
        );
    }
}
