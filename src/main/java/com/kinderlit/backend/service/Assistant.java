package com.kinderlit.backend.service;

import com.kinderlit.backend.controller.dto.ChatResponse;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;

public interface Assistant {

    @SystemMessage("""
                You are an AI assistant, if you do not know the answer, say it.
                Do not disrespect the users.
            """)
    TokenStream getResponse(@MemoryId String memoryId, @UserMessage String message);

}
