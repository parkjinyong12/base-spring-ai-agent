package com.example.agent.controller;

import com.example.agent.dto.ChatOptionsRequest;
import com.example.agent.dto.ChatOptionsResponse;
import com.example.agent.service.ChatOptionsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ChatOptions", description = "AI 모델 옵션 CRUD")
@RestController
@RequestMapping("/options")
public class ChatOptionsController {

    private final ChatOptionsService chatOptionsService;

    public ChatOptionsController(ChatOptionsService chatOptionsService) {
        this.chatOptionsService = chatOptionsService;
    }

    @Operation(summary = "전체 조회")
    @GetMapping
    public List<ChatOptionsResponse> getAll() {
        return chatOptionsService.getAll().stream()
                .map(ChatOptionsResponse::from)
                .toList();
    }

    @Operation(summary = "생성")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChatOptionsResponse create(@RequestBody ChatOptionsRequest request) {
        return ChatOptionsResponse.from(chatOptionsService.create(request));
    }

    @Operation(summary = "수정")
    @PutMapping("/{id}")
    public ChatOptionsResponse update(@PathVariable Long id, @RequestBody ChatOptionsRequest request) {
        return ChatOptionsResponse.from(chatOptionsService.update(id, request));
    }

    @Operation(summary = "삭제", description = "defaultFlag=true인 옵션은 삭제 불가")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        chatOptionsService.delete(id);
    }

    @Operation(summary = "기본 옵션 변경")
    @PutMapping("/{id}/default")
    public ChatOptionsResponse setDefault(@PathVariable Long id) {
        return ChatOptionsResponse.from(chatOptionsService.setDefault(id));
    }
}
