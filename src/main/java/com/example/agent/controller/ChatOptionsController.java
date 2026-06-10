package com.example.agent.controller;

import com.example.agent.dto.ChatOptionsResponse;
import com.example.agent.service.ChatOptionsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/options")
public class ChatOptionsController {

    private final ChatOptionsService chatOptionsService;

    public ChatOptionsController(ChatOptionsService chatOptionsService) {
        this.chatOptionsService = chatOptionsService;
    }

    @GetMapping
    public List<ChatOptionsResponse> getAll() {
        return chatOptionsService.getAll().stream()
                .map(ChatOptionsResponse::from)
                .toList();
    }

    @PutMapping("/{id}/default")
    public ChatOptionsResponse setDefault(@PathVariable Long id) {
        return ChatOptionsResponse.from(chatOptionsService.setDefault(id));
    }
}
