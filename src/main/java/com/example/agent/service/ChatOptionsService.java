package com.example.agent.service;

import com.example.agent.domain.AiChatOptions;
import com.example.agent.domain.AiChatOptionsRepository;
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
    public AiChatOptions setDefault(Long id) {
        // 기존 default 해제
        repository.findByDefaultFlagTrue()
                .ifPresent(opt -> opt.setDefaultFlag(false));

        // 새 default 설정
        AiChatOptions target = getById(id);
        target.setDefaultFlag(true);
        return target;
    }
}
