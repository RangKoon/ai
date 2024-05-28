//package com.ai.yummyheap.example;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.net.http.HttpHeaders;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class YHService {
//    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
//    private static final String API_KEY = "API_KEY";
//    private final RestTemplate restTemplate;
//    private final ObjectMapper objectMapper;
//
//    @Autowired
//    public YHService(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
//        this.restTemplate = restTemplateBuilder.build();
//        this.objectMapper = objectMapper;
//    }
//
//    // DB에서 수질 정보 가져오는 메소드
//    public String getWaterInfo(String id) {
//        String url = "http://localhost:8080/waterinfo/yearly/" + id;
//        //
//        ...
//    }
//
//    //DB에서 급이 기록 가져오는 메소드
//    public String getFoodRecord() {
//        String url = "http://localhost:8080/fish-info/food-record";
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
//        return response.getBody();
//    }
//
//    //DB에서 일출,일몰 시간 가져오는 메소드
//    public String getSunriseSunset() {
//        String url = "http://localhost:8080/weather/sunrise-sunset";
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
//        return response.getBody();
//    }
//
//    //질문하기 전에GPT에 데이터Input
//    public String prepareMessage(String waterInfo, String foodRecord, String sunriseSunset) {
//        return "Here is the water info for the year: " + waterInfo + "\n"
//                + "Here is the feeding record: " + foodRecord + "\n"
//                + "Here is the sunrise and sunset info for today: " + sunriseSunset + "\n";
//    }
//
//    /*//일출, 일몰 시간String -> LocalTime으로 파싱
//    private SunriseSunsetData parseSunriseSunset(String sunriseSunset) {
//        // ...
//    }*/
//
//    //질의응답 매뉴얼
//    public String askGPT(String question) {
//        try {
//            // Data Input
//            String waterInfo = getWaterInfo("1");
//            String foodRecord = getFoodRecord();
//            String sunriseSunset = getSunriseSunset();
//            String preMessage = prepareMessage(waterInfo, foodRecord, sunriseSunset);
//
//            String modifiedQuestion = question.toLowerCase();
//            // 다음과 같은 질문을 포함한 경우의 답변 매뉴얼 생성
//            if (modifiedQuestion.contains("밥을 언제 줄까?")) {
//                SunriseSunsetData sunriseSunsetData = parseSunriseSunset(sunriseSunset);
//                if (sunriseSunsetData != null) {
//                    LocalDateTime sunriseDateTime = sunriseSunsetData.getSunriseDateTime();
//                    LocalDateTime sunsetDateTime = sunriseSunsetData.getSunsetDateTime();
//
//                    // 밥 주는 시간 계산
//
//                    // 밥 주는 시간을 문자열로 포맷팅
//
//                    // 응답에 밥 주는 시간 추가하기
//                    preMessage += "광어에게 밥을 줄 시간은 아침: " + formattedFeedingTime1 + ", 저녁: " + formattedFeedingTime2 + "입니다.";
//                } else {
//                    // 오류 처리 로직 추가
//                }
//            } else if( // 조건)
//            // 답변에 포함시킬 내용
//            // ...
//        }
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", "Bearer " + API_KEY);
//
//        List<Map<String, String>> messages = new ArrayList<>();
//
//        // System message with preMessage
//        Map<String, String> systemMessage = new HashMap<>();
//        systemMessage.put("role", "system");
//        systemMessage.put("content", preMessage);
//        messages.add(systemMessage);
//
//        // User message with the question
//        Map<String, String> userMessage = new HashMap<>();
//        userMessage.put("role", "user");
//        userMessage.put("content", question);
//        messages.add(userMessage);
//
//        // 답변 가능한 최대 글자수, 사용 모델 설정
//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("messages", messages);
//        requestBody.put("max_tokens", 500);
//        requestBody.put("model", "gpt-3.5-turbo");
//
//        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), headers);
//        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);
//
//        if (response.getStatusCode() == HttpStatus.OK) {
//            System.out.println("Response: " + response.getBody());
//            Map<String, Object> responseBody = objectMapper.readValue(response.getBody(), Map.class);
//            if (responseBody.containsKey("choices")) {
//                List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
//                if (choices != null && !choices.isEmpty() && choices.get(0).containsKey("message")) {
//                    Map<String, Object> messageResponse = (Map<String, Object>) choices.get(0).get("message");
//                    if (messageResponse.containsKey("content")) {
//                        String answer = (String) messageResponse.get("content");
//                        return answer.trim();
//                    }
//                }
//            }
//        } else {
//            throw new RuntimeException("ChatGPT API 요청 실패: " + response.getStatusCode());
//        }
//    } catch (Exception e) {
//        throw new RuntimeException("ChatGPT API 호출 중 오류 발생", e);
//    }
//        throw new RuntimeException("ChatGPT API 호출에서 예상치 못한 상황 발생");
//}
//}
