package com.example.agent.agent;

import com.example.agent.domain.AiChatOptions;
import com.example.agent.service.ChatOptionsService;
import org.springframework.ai.anthropic.AnthropicChatOptions;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class SimpleAgent {

    private final ChatModelFactory chatModelFactory;
    private final ChatOptionsService chatOptionsService;

    public SimpleAgent(ChatModelFactory chatModelFactory, ChatOptionsService chatOptionsService) {
        this.chatModelFactory = chatModelFactory;
        this.chatOptionsService = chatOptionsService;
    }

    public String chat(String userMessage, Long optionsId) {
        AiChatOptions dbOptions = optionsId != null
                ? chatOptionsService.getById(optionsId)
                : chatOptionsService.getDefault();

        ChatModel chatModel = chatModelFactory.getOrCreate(dbOptions.getProvider(), dbOptions.getApiKey());
        ChatOptions chatOptions = buildChatOptions(dbOptions);

        Prompt prompt = new Prompt(new UserMessage(userMessage), chatOptions);
        return chatModel.call(prompt)
                .getResult()
                .getOutput()
                .getText();
    }

    private ChatOptions buildChatOptions(AiChatOptions dbOptions) {
        return switch (dbOptions.getProvider()) {
            case "anthropic" -> AnthropicChatOptions.builder()
                    .model(dbOptions.getModel())
                    .maxTokens(dbOptions.getMaxTokens())
                    .temperature(dbOptions.getTemperature())
                    .build();
            case "openai" -> OpenAiChatOptions.builder()
                    .model(dbOptions.getModel())
                    .maxTokens(dbOptions.getMaxTokens())
                    .temperature(dbOptions.getTemperature())
                    .build();
            default -> throw new IllegalArgumentException("지원하지 않는 provider: " + dbOptions.getProvider());
        };
    }
}
