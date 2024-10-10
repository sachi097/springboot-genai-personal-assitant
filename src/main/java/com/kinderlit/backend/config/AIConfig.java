package com.kinderlit.backend.config;

import com.kinderlit.backend.service.Assistant;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.ollama.OllamaContainer;

import java.time.Duration;

@Configuration
public class AIConfig {
    final String MODEL_NAME = "llama3";
    @Bean
    public Assistant assistant(){
        return AiServices.builder(Assistant.class)
                .streamingChatLanguageModel(chatLanguageModel())
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(2048))
                .build();
    }

    @Bean
    public StreamingChatLanguageModel chatLanguageModel(){
        OllamaContainer ollama = getOllamaContainer();
        return OllamaStreamingChatModel.builder()
                .baseUrl(baseUrl(ollama))
                .modelName(MODEL_NAME)
                .temperature(1.0)
                .timeout(Duration.ofHours(1))
                .build();
    }

    private OllamaContainer getOllamaContainer(){
        OllamaContainer ollama = new OllamaContainer("ollama/ollama:0.1.26");
        ollama.start();
        try {
            ollama.execInContainer("ollama", "pull", MODEL_NAME);
            ollama.commitToImage(MODEL_NAME);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ollama;
    }

    private static String baseUrl(GenericContainer<?> ollama) {
        return String.format("http://%s:%d", ollama.getHost(), ollama.getFirstMappedPort());
    }
}
