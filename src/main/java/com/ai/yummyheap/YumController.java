package com.ai.yummyheap;

import com.ai.yummyheap.model.BaseResponse;
import com.ai.yummyheap.model.ChatGPTRequest;
import com.ai.yummyheap.model.ChatGPTResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/yummy-heap")
public class YumController {
    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Value("${openai.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/chat")
    public ResponseEntity<BaseResponse<String>> chat(@RequestParam(name = "prompt") String prompt) {
        try {
            // ChatGPTRequest 생성
            ChatGPTRequest request = new ChatGPTRequest(model, prompt);

            // HTTP 요청 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.setBearerAuth(apiKey);

            // HTTP 요청 엔터티 생성
            HttpEntity<ChatGPTRequest> entity = new HttpEntity<>(request, headers);

            // HTTP POST 요청 보내기
            ResponseEntity<String> responseEntity = restTemplate.exchange(apiURL, HttpMethod.POST, entity, String.class);

            // HTTP 응답 상태코드에 따른 처리
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                // 성공적인 응답일 경우ChatGPTResponse로 변환
                ChatGPTResponse chatGPTResponse = objectMapper.readValue(responseEntity.getBody(), ChatGPTResponse.class);

                // ChatGPTResponse가 유효한지 확인하고 결과 반환
                if (chatGPTResponse != null && chatGPTResponse.getChoices() != null && !chatGPTResponse.getChoices().isEmpty()) {
                    return ResponseEntity.ok(BaseResponse.success("Success", chatGPTResponse.getChoices().get(0).getMessage().getContent()));
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(BaseResponse.success("Invalid response from ChatGPT API", null));
                }
            } else {
                // 실패한 경우 에러 응답 반환
                return ResponseEntity.status(responseEntity.getStatusCode())
                        .body(BaseResponse.success("Error occurred", responseEntity.getBody()));
            }
        } catch (Exception e) {
            // 예외 발생 시 에러 응답 반환
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(BaseResponse.success("Error occurred: " + e.getMessage(), null));
        }
    }
}

//http://localhost:8080/yummy-heap/chat?prompt=%EB%8C%80%EB%8B%B5%EC%A2%80


