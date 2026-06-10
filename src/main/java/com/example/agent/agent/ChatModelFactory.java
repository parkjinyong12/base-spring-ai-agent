package com.example.agent.agent;

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

    // provider:apiKey 조합으로 캐싱 - 요청마다 HTTP 클라이언트를 새로 생성하지 않기 위해
    private final ConcurrentHashMap<String, ChatModel> cache = new ConcurrentHashMap<>();

    public ChatModel getOrCreate(String provider, String apiKey) {
        return cache.computeIfAbsent(provider + ":" + apiKey, k -> create(provider, apiKey));
    }

    private ChatModel create(String provider, String apiKey) {
        return switch (provider) {
            case "anthropic" -> new AnthropicChatModel(
                    new AnthropicApi(apiKey),
                    AnthropicChatOptions.builder().build()
            );
            case "openai" -> new OpenAiChatModel(
                    new OpenAiApi(apiKey),
                    OpenAiChatOptions.builder().build()
            );
            default -> throw new IllegalArgumentException("지원하지 않는 provider: " + provider);
        };
    }
}
