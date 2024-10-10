package com.kinderlit.backend.controller.dto;

import lombok.Data;

@Data
public class ChatResponse {
    String userId;
    String content;
    Boolean latex;
}
