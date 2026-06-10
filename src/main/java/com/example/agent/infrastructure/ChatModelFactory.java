package com.example.agent.infrastructure;

import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.anthropic.AnthropicChatOptions;
import org.springframework.ai.anthropic.api.AnthropicApi;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatModelFactory {

    private final ConcurrentHashMap<String, ChatModel> cache = new ConcurrentHashMap<>();

    public ChatModel getOrCreate(String provider, String apiKey) {
        return cache.computeIfAbsent(provider + ":" + apiKey, k -> create(provider, apiKey));
    }

    private ChatModel create(String provider, String apiKey) {
        return switch (provider) {
            case "anthropic" -> AnthropicChatModel.builder()
                    .anthropicApi(AnthropicApi.builder()
                            .apiKey(apiKey)
                            .build())
                    .defaultOptions(AnthropicChatOptions.builder().build())
                    .build();
            case "openai" -> OpenAiChatModel.builder()
                    .openAiApi(OpenAiApi.builder()
                            .apiKey(apiKey)
                            .build())
                    .defaultOptions(OpenAiChatOptions.builder().build())
                    .build();
            default -> throw new IllegalArgumentException("지원하지 않는 provider: " + provider);
        };
    }
}
