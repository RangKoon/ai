package com.ai.yummyheap.view;

import com.ai.yummyheap.model.ChatGPTResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResponseController {

    @GetMapping("/response")
    public String showResponse(Model model) {
        // ChatGPTResponse 객체를 생성하여 모델에 추가
        ChatGPTResponse response = new ChatGPTResponse();
//        response.setContent("This is the response from ChatGPT.");
        model.addAttribute("response", response);

        // response.html 파일을 반환하여 해당HTML을 렌더링하도록 함
        return "response";
    }
}