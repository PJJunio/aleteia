package com.paulo.aleteia.service;

import com.paulo.aleteia.dto.TelegramMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TelegramService {
    private static final Logger logger = LoggerFactory.getLogger(TelegramService.class);
    private final WebClient webClient;

    private final String telegramToken;
    private final String chatId;

    public TelegramService(WebClient.Builder builder,
                           @Value("${TELEGRAM_BOT_TOKEN}") String telegramToken,
                           @Value("${TELEGRAM_CHAT_ID}") String chatId) {
        this.webClient = builder.build();
        this.telegramToken = telegramToken;
        this.chatId = chatId;
    }

    public Mono<String> processar(String mensagem) {
        // Trunca a mensagem se for muito longa (limite do Telegram é 4096)
        String textoEnviado = mensagem;
        if (mensagem != null && mensagem.length() > 4000) {
            logger.warn("Mensagem muito longa ({} chars), truncando para 4000...", mensagem.length());
            textoEnviado = mensagem.substring(0, 4000) + "... [truncado]";
        }

        var msg = new TelegramMessageDto(chatId, textoEnviado);
        String url = "https://api.telegram.org/bot" + telegramToken + "/sendMessage";
        logger.info("Enviando mensagem para a URL do Telegram: {}", url);

        return this.webClient.post()
                .uri(url)
                .bodyValue(msg)
                .retrieve()
                .bodyToMono(String.class)
                .doOnError(error -> {
                    logger.error("Erro ao enviar mensagem para o Telegram API: {}", error.getMessage());
                    if (error instanceof org.springframework.web.reactive.function.client.WebClientResponseException webEx) {
                        logger.error("Corpo da resposta de erro do Telegram: {}", webEx.getResponseBodyAsString());
                    }
                });
    }
}
