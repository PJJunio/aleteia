package com.paulo.aleteia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TelegramMessageDto(
        @JsonProperty("chat_id")
        String chatId,
        String text
) {
}
