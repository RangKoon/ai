package com.ai.yummyheap;
import com.ai.yummyheap.ai.AI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class YummyheapApplication {
	public static void main(String[] args) {
		AI ai=new AI();
		ai.setImage("C:\\Users\\gjw19\\Downloads\\두부.jpeg");
		List<String> detectedClasses =ai.ai_ex();
		for(int i=0;i<detectedClasses.size();i++){
			System.out.println(detectedClasses.get(i)+"\n");
		}
	}
}
