package com.ai.yummyheap;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("test")
@Service
public class MockYumService {
    public String chat(String prompt) {
        return "This is a mock response for prompt: " + prompt;
    }
}