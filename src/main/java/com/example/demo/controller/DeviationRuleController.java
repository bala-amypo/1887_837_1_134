package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "deviation_rules", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ruleCode")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ruleCode;

    @Column(nullable = false)
    private String surgeryType;

    @Column(nullable = false)
    private String parameter;

    @Column(nullable = false)
    private Integer threshold;

    @Column(nullable = false)
    private String severity;

    @Column(nullable = false)
    private Boolean active = true;
}
