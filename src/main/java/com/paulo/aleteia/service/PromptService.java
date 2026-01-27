package com.paulo.aleteia.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class PromptService {

    private static final Logger logger = LoggerFactory.getLogger(PromptService.class);

    private final Client client;
    private final TelegramService telegramService;

    public PromptService(@Value("${GEMINI_API_TOKEN}") String geminiToken, TelegramService telegramService) {
        this.client = Client.builder().apiKey(geminiToken).build();
        this.telegramService = telegramService;
    }

    public void processar(String prompt) {
        logger.info("Processando prompt...");

        Mono.fromCallable(() -> {
            GenerateContentResponse response = client.models.generateContent("gemini-flash-latest", prompt, null);
            return response.text();
        })
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(resposta -> {
                    logger.info("Resposta do Gemini recebida, enviando para o Telegram...");
                    return telegramService.processar(resposta);
                })
                .subscribe(
                        resultado -> logger.info("Mensagem enviada ao Telegram com sucesso: {}", resultado),
                        erro -> logger.error("Ocorreu um erro na chamada da API (Gemini ou Telegram)", erro));
    }
}
