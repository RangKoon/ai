package com.ai.yummyheap;
import com.ai.yummyheap.ai.AI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YummyheapApplication {
	public static void main(String[] args) {
		AI ai=new AI();
		ai.setImage("C:\\Users\\gjw19\\Downloads\\두부.jpeg");
		String s= ai.getRecipesAsString();
		System.out.println(s);
	}
}
