package com.kinderlit.backend.controller;

import com.kinderlit.backend.controller.dto.ChatRequest;
import com.kinderlit.backend.controller.dto.ChatResponse;
import com.kinderlit.backend.entity.Chat;
import com.kinderlit.backend.service.GenerativeAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class GenerativeAIController {
    @Autowired
    private GenerativeAIService genAIService;

    @PostMapping("/getChatResponse")
    public StreamingResponseBody getChatResponse(@RequestBody ChatRequest request){
        List<Chat> messages = request.messages();
        Chat lastMessage = request.messages().get(messages.size() - 1);
        return genAIService.streamChat(lastMessage);
    }
}
