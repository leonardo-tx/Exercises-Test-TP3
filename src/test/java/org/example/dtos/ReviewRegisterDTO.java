package org.example.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReviewRegisterDTO {
    private String title;
    private String content;
    private int stars;
}
