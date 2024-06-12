package com.ai.yummyheap.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGPTRequest {
    private String model;
    private String prompt;
    private int max_tokens;
    private double temperature;
}
