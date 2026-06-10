package com.example.agent.agent;

import com.example.agent.domain.AiChatOptions;
import com.example.agent.service.ChatOptionsService;
import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.anthropic.AnthropicChatOptions;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class SimpleAgent {

    private final AnthropicChatModel chatModel;
    private final ChatOptionsService chatOptionsService;

    public SimpleAgent(AnthropicChatModel chatModel, ChatOptionsService chatOptionsService) {
        this.chatModel = chatModel;
        this.chatOptionsService = chatOptionsService;
    }

    public String chat(String userMessage, Long optionsId) {
        AiChatOptions dbOptions = optionsId != null
                ? chatOptionsService.getById(optionsId)
                : chatOptionsService.getDefault();

        AnthropicChatOptions chatOptions = AnthropicChatOptions.builder()
                .model(dbOptions.getModel())
                .maxTokens(dbOptions.getMaxTokens())
                .temperature(dbOptions.getTemperature())
                .build();

        Prompt prompt = new Prompt(new UserMessage(userMessage), chatOptions);
        return chatModel.call(prompt)
                .getResult()
                .getOutput()
                .getText();
    }
}
