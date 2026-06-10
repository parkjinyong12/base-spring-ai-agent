package com.example.agent.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AiChatOptionsRepository extends JpaRepository<AiChatOptions, Long> {

    Optional<AiChatOptions> findByDefaultFlagTrue();
}
