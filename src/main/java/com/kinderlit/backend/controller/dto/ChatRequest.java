package com.kinderlit.backend.controller.dto;

import com.kinderlit.backend.entity.Chat;

import java.util.List;

public record ChatRequest(List<Chat> messages) {
}
