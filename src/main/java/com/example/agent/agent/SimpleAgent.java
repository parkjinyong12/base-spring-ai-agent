package com.example.agent.agent;

import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class SimpleAgent {

    private final AnthropicChatModel chatModel;

    public SimpleAgent(AnthropicChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String chat(String userMessage) {
        Prompt prompt = new Prompt(new UserMessage(userMessage));
        return chatModel.call(prompt)
                .getResult()
                .getOutput()
                .getText();
    }
}
