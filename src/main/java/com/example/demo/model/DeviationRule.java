package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviationRule {

    private Long id;
    private String ruleCode;
    private String parameter;

    private Integer threshold;   // ✅ change
    private String severity;
    private Boolean active;
}
