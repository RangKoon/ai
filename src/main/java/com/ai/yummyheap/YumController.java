package com.ai.yummyheap;

import com.ai.yummyheap.model.ChatGPTRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/yummy-heap")
public class YumController {

    private static final Logger logger = LoggerFactory.getLogger(YumController.class);

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

    @PostMapping("/chat")
    public ResponseEntity<String> chat(@RequestBody ChatGPTRequest request) {
        try {
            // 모델 설정
            request.setModel(model);

            // HTTP 요청 본문 출력 (디버깅용)
            String requestJson = objectMapper.writeValueAsString(request);
            logger.debug("Request JSON: {}", requestJson);

            // HTTP 요청 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);

            // HTTP 요청 엔터티 생성
            HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

            // HTTP POST 요청 보내기
            ResponseEntity<String> responseEntity = restTemplate.exchange(apiURL, HttpMethod.POST, entity, String.class);

            // 응답 내용 로깅
            logger.debug("Response Status Code: {}", responseEntity.getStatusCode());
            logger.debug("Response Body: {}", responseEntity.getBody());

            // HTTP 응답 상태코드에 따른 처리
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok(responseEntity.getBody());
            } else {
                // 실패한 경우 에러 응답 반환
                return ResponseEntity.status(responseEntity.getStatusCode())
                        .body("Error occurred: " + responseEntity.getBody());
            }
        } catch (HttpClientErrorException e) {
            // 예외 발생 시 에러 응답 반환
            logger.error("HTTP Client Error: Status code: {}, Response body: {}", e.getStatusCode(), e.getResponseBodyAsString());
            return ResponseEntity.status(e.getStatusCode())
                    .body("Error occurred: " + e.getResponseBodyAsString());
        } catch (Exception e) {
            // 일반 예외 발생 시 에러 응답 반환
            logger.error("General Error: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred: " + e.getMessage());
        }
    }
}

/*
curl -X POST http://localhost:8080/yummy-heap/chat \
  -H "Content-Type: application/json" \
  -d '{
    "model": "davinci-002",
    "prompt": "Say this is a test",
    "max_tokens": 7,
    "temperature": 0.0
  }'

* */
