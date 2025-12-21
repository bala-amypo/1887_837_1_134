package com.example.demo.service.impl;

import com.example.demo.model.DeviationRule;
import com.example.demo.repository.DeviationRuleRepository;
import com.example.demo.service.DeviationRuleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviationRuleServiceImpl implements DeviationRuleService {

    private final DeviationRuleRepository repository;

    public DeviationRuleServiceImpl(DeviationRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public DeviationRule createRule(DeviationRule rule) {
        if (rule.getThreshold() <= 0) {
            throw new IllegalArgumentException("Threshold must be");
        }
        return repository.save(rule);
    }

    @Override
    public Optional<DeviationRule> getRuleById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<DeviationRule> getRulesBySurgery(String surgeryType) {
        return repository.findBySurgeryType(surgeryType);
    }

    @Override
    public List<DeviationRule> getActiveRules() {
        return repository.findByActiveTrue();
    }

    @Override
    public DeviationRule updateRule(Long id, DeviationRule rule) {
        DeviationRule existing = repository.findById(id).orElseThrow();
        existing.setThreshold(rule.getThreshold());
        existing.setSeverity(rule.getSeverity());
        existing.setActive(rule.getActive());
        return repository.save(existing);
    }
}
