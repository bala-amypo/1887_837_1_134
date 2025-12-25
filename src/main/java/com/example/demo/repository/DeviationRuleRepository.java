package com.example.demo.repository;

import com.example.demo.model.DeviationRule;

import java.util.List;
import java.util.Optional;

public interface DeviationRuleRepository {

    DeviationRule save(DeviationRule rule);

    Optional<DeviationRule> findById(Long id);

    Optional<DeviationRule> findByRuleCode(String ruleCode);

    List<DeviationRule> findByActiveTrue();
}
