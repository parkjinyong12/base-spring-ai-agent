package com.example.agent.controller;

import com.example.agent.dto.ChatOptionsRequest;
import com.example.agent.dto.ChatOptionsResponse;
import com.example.agent.service.ChatOptionsService;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChatOptionsResponse create(@RequestBody ChatOptionsRequest request) {
        return ChatOptionsResponse.from(chatOptionsService.create(request));
    }

    @PutMapping("/{id}")
    public ChatOptionsResponse update(@PathVariable Long id, @RequestBody ChatOptionsRequest request) {
        return ChatOptionsResponse.from(chatOptionsService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        chatOptionsService.delete(id);
    }

    @PutMapping("/{id}/default")
    public ChatOptionsResponse setDefault(@PathVariable Long id) {
        return ChatOptionsResponse.from(chatOptionsService.setDefault(id));
    }
}
