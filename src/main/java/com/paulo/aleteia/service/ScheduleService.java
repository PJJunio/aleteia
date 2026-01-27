package com.paulo.aleteia.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class ScheduleService {

    private final PromptService promptService;
    private final ResourceLoader resourceLoader;

    public ScheduleService(PromptService promptService, ResourceLoader resourceLoader) {
        this.promptService = promptService;
        this.resourceLoader = resourceLoader;
    }

    @Scheduled(cron = "0 30 6 * * *")
    public void morningTask() {
        executeTask("classpath:prompt/manha.txt");
    }

    @Scheduled(cron = "0 0 12 * * *")
    public void afternoonTask() {
        executeTask("classpath:prompt/tarde.txt");
    }

    @Scheduled(cron = "0 0 19 * * *")
    public void nightTask() {
        executeTask("classpath:prompt/noite.txt");
    }

    private void executeTask(String resourcePath) {
        try {
            Resource resource = resourceLoader.getResource(resourcePath);
            if (!resource.exists()) {
                String fallbackPath = resourcePath.replace("classpath:", "file:src/main/resources/");
                Resource fallbackResource = resourceLoader.getResource(fallbackPath);
                if (fallbackResource.exists()) {
                    resource = fallbackResource;
                }
            }

            if (resource.exists()) {
                String prompt = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                promptService.processar(prompt);
            } else {
                System.err.println(
                        "ERRO CRITICO: Arquivo de prompt nao encontrado nem no classpath nem em src/main/resources: "
                                + resourcePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
