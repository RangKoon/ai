package com.ai.yummyheap.ai;

import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AI {
    private final String pythonScriptPath = "src/main/java/com/ai/yummyheap/ai/send.py";
    @Setter
    private String image = null;
    public List<String> ai_ex() {
        List<String> results = new ArrayList<>();
        String pythonInterpreter = "C:\\Users\\gjw19\\AppData\\Local\\Programs\\Python\\Python312\\python.exe"; // Python interpreter 경로 설정
        ProcessBuilder processBuilder = new ProcessBuilder(pythonInterpreter, pythonScriptPath, image);
        processBuilder.redirectErrorStream(true);
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                results.add(line);
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Python 스크립트가 오류 코드로 종료됨: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return results;
    }
    public String getRecipesAsString() {
        List<String> output = ai_ex();

        StringBuilder recipes = new StringBuilder();
        for (String line : output) {
            if (line.startsWith("Detected:") || line.contains("레시피")) {
                recipes.append(line).append("\n");
            }
        }
        return recipes.toString();
    }
}
