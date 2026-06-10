package com.example.agent.controller;

import com.example.agent.agent.SimpleAgent;
import com.example.agent.dto.ChatRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Agent", description = "AI 채팅")
@RestController
@RequestMapping("/agent")
public class AgentController {

    private final SimpleAgent simpleAgent;

    public AgentController(SimpleAgent simpleAgent) {
        this.simpleAgent = simpleAgent;
    }

    @Operation(summary = "채팅", description = "optionsId 미입력 시 default 옵션 사용")
    @PostMapping("/chat")
    public String chat(@RequestBody ChatRequest request) {
        return simpleAgent.chat(request.message(), request.optionsId());
    }
}
