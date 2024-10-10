package com.kinderlit.backend.service;

import com.kinderlit.backend.entity.Chat;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public interface IGenerativeAIService {
    public StreamingResponseBody streamChat(Chat chat);
}
