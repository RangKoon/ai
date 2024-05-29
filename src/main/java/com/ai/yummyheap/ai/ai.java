package com.ai.yummyheap.ai;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class ai {
    //나중에 모델 변경
    String pythonScriptPath = "C:\\Users\\gjw19\\IdeaProjects\\YummyHeap\\src\\main\\java\\com\\ai\\yummyheap\\ai\\send.py";
    String image=null;
    //실행 시키면 객체 리스트 반환
    List<String> ai_ex(){
        ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath, image);
        processBuilder.redirectErrorStream(true);
        Process process = null;
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        List<String> detectedClasses = new ArrayList<>();
        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                int cls = Integer.parseInt(line);
                detectedClasses.add(String.valueOf(cls));
            } catch (NumberFormatException e) {
                System.err.println("Invalid class detected: " + line);
            }
        }
        return detectedClasses;
    }

    void setImage(String s){
        image=s;
    }
}
