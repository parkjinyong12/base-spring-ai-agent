package com.example.agent.service;

import com.example.agent.domain.AiChatOptions;
import com.example.agent.domain.AiChatOptionsRepository;
import com.example.agent.dto.ChatOptionsRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ChatOptionsService {

    private final AiChatOptionsRepository repository;

    public ChatOptionsService(AiChatOptionsRepository repository) {
        this.repository = repository;
    }

    public AiChatOptions getDefault() {
        return repository.findByDefaultFlagTrue()
                .orElseThrow(() -> new IllegalStateException("Default ChatOptions가 설정되지 않았습니다."));
    }

    public AiChatOptions getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ChatOptions를 찾을 수 없습니다. id=" + id));
    }

    public List<AiChatOptions> getAll() {
        return repository.findAll();
    }

    @Transactional
    public AiChatOptions create(ChatOptionsRequest request) {
        return repository.save(new AiChatOptions(
                request.name(),
                request.provider(),
                request.apiKey(),
                request.model(),
                request.maxTokens(),
                request.temperature(),
                false
        ));
    }

    @Transactional
    public AiChatOptions update(Long id, ChatOptionsRequest request) {
        AiChatOptions target = getById(id);
        target.update(
                request.name(),
                request.provider(),
                request.apiKey(),
                request.model(),
                request.maxTokens(),
                request.temperature()
        );
        return target;
    }

    @Transactional
    public void delete(Long id) {
        AiChatOptions target = getById(id);
        if (target.isDefaultFlag()) {
            throw new IllegalStateException("Default로 설정된 옵션은 삭제할 수 없습니다.");
        }
        repository.delete(target);
    }

    @Transactional
    public AiChatOptions setDefault(Long id) {
        repository.findByDefaultFlagTrue()
                .ifPresent(opt -> opt.setDefaultFlag(false));

        AiChatOptions target = getById(id);
        target.setDefaultFlag(true);
        return target;
    }
}
