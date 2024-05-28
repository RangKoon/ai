//package com.ai.yummyheap.example;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@CrossOrigin
//@RestController
//@RequestMapping("/yummy-heap")
//public class YHController {
//    // Service의class file 명: YHService
//    private final YHService yhService;
//
//    @Autowired
//    public YHController(YHService yhService) {
//        this.yhService = yhService;
//    }
//
//    @PostMapping("/chat/completions")
//    public String askGPT(@RequestBody String question) {
//        return yhService.askGPT(question);
//    }
//}