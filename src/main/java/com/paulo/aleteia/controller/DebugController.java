package com.paulo.aleteia.controller;

import com.paulo.aleteia.service.ScheduleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/debug")
public class DebugController {

    private final ScheduleService scheduleService;

    public DebugController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/morning")
    public String triggerMorningTask() {
        scheduleService.morningTask();
        return "Tarefa da manha iniciada. Verifique os logs da aplicacao.";
    }

    @GetMapping("/afternoon")
    public String triggerAfternoonTask() {
        scheduleService.afternoonTask();
        return "Tarefa da tarde iniciada. Verifique os logs da aplicacao.";
    }

    @GetMapping("/night")
    public String triggerNightTask() {
        scheduleService.nightTask();
        return "Tarefa da noite iniciada. Verifique os logs da aplicacao.";
    }
}
