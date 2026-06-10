package com.example.agent.config;

import com.example.agent.domain.AiChatOptions;
import com.example.agent.domain.AiChatOptionsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AiChatOptionsRepository repository;

    public DataInitializer(AiChatOptionsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) return;

        String anthropicKey = System.getenv("ANTHROPIC_API_KEY");
        String openAiKey = System.getenv("OPENAI_API_KEY");

        repository.saveAll(List.of(
            new AiChatOptions("claude-standard", "anthropic", anthropicKey, "claude-opus-4-8",    1024, 1.0, true),
            new AiChatOptions("claude-creative", "anthropic", anthropicKey, "claude-opus-4-8",    2048, 1.0, false),
            new AiChatOptions("claude-fast",     "anthropic", anthropicKey, "claude-haiku-4-5-20251001", 512, 0.5, false),
            new AiChatOptions("gpt-4o",          "openai",    openAiKey,   "gpt-4o",             1024, 1.0, false)
        ));
    }
}
