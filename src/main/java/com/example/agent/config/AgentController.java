package com.example.agent.config;

import com.example.agent.agent.SimpleAgent;
import com.example.agent.dto.ChatRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agent")
public class AgentController {

    private final SimpleAgent simpleAgent;

    public AgentController(SimpleAgent simpleAgent) {
        this.simpleAgent = simpleAgent;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody ChatRequest request) {
        return simpleAgent.chat(request.message(), request.optionsId());
    }
}
