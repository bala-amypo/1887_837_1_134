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
    private String parameter;
    private int threshold;
    private String severity;
    private Boolean active;
}
