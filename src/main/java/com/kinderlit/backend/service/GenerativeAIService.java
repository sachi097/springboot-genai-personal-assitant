package com.kinderlit.backend.service;

import com.kinderlit.backend.entity.Chat;
import com.kinderlit.backend.repo.ChatsRepository;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.service.TokenStream;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

@Service
public class GenerativeAIService implements IGenerativeAIService {

    @Autowired
    private final Assistant assistant;

    @Autowired
    private final ChatsRepository chatsRepository;

    GenerativeAIService(Assistant assistant, ChatsRepository chatsRepository){
        this.assistant = assistant;
        this.chatsRepository = chatsRepository;
    }

    @Override
    public StreamingResponseBody streamChat(Chat chat) {
        chatsRepository.saveChat(chat);
        return outputStream -> {
            TokenStream tokenStream = assistant.getResponse(chat.getUserId(), chat.getContent());
            CompletableFuture<Response<AiMessage>> future = new CompletableFuture<>();
            tokenStream.onNext(data -> {
                        try {
                            outputStream.write(data.getBytes(StandardCharsets.UTF_8));
                            outputStream.flush();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .onComplete(responseAiMessage -> {
                        Chat assistantResponse = new Chat();
                        assistantResponse.setUserId(chat.getUserId());
                        assistantResponse.setContent(responseAiMessage.content().text());
                        assistantResponse.setRole("assistant");
                        assistantResponse.setTimestamp(System.currentTimeMillis());
                        chatsRepository.saveChat(assistantResponse);
                        future.complete(null);
                    })
                    .onError(error -> {
                        System.out.println(error.getMessage());
                        future.completeExceptionally(error);
                    }).start();
            future.join();
        };
    }

}
