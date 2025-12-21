package com.example.demo.controller;

import com.example.demo.model.DeviationRule;
import com.example.demo.service.DeviationRuleService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deviation-rules")
public class DeviationRuleController {

    private final DeviationRuleService service;

    public DeviationRuleController(DeviationRuleService service) {
        this.service = service;
    }

    @PostMapping
    public DeviationRule create(@Valid @RequestBody DeviationRule rule) {
        return service.createRule(rule);
    }

    @PutMapping("/{id}")
    public DeviationRule update(
            @PathVariable Long id,
            @Valid @RequestBody DeviationRule rule) {
        return service.updateRule(id, rule);
    }

    @GetMapping("/active")
    public List<DeviationRule> getActive() {
        return service.getActiveRules();
    }

    @GetMapping("/surgery/{surgeryType}")
    public List<DeviationRule> getBySurgery(@PathVariable String surgeryType) {
        return service.getRulesBySurgery(surgeryType);
    }
}
