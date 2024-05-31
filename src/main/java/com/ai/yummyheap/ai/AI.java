package com.ai.yummyheap.ai;

import lombok.Setter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AI {
    private final String pythonScriptPath = "C:\\Users\\gjw19\\IdeaProjects\\YummyHeap\\src\\main\\java\\com\\ai\\yummyheap\\ai\\send.py";
    @Setter
    private String image = null;

    // 클래스 번호와 이름의 매핑 정보
    private final Map<Integer, String> classNames = createClassNamesMap();

    // 클래스 번호와 이름의 매핑 정보를 생성하는 메서드
    private Map<Integer, String> createClassNamesMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "Eggplant");
        map.put(1, "Crabstick");
        map.put(2, "Sweet_potato");
        map.put(3, "Mackerel");
        map.put(4, "Seaweed");
        map.put(5, "Yellow_paprika");
        map.put(6, "Green_pepper");
        map.put(7, "Egg");
        map.put(8, "Chicken_breast");
        map.put(9, "Carrot");
        map.put(10, "Green_onion");
        map.put(11, "Tofu");
        map.put(12, "Garlic");
        map.put(13, "Rice");
        map.put(14, "Cabbage");
        map.put(15, "Kimchi");
        map.put(16, "Mushroom");
        map.put(17, "Bacon");
        map.put(18, "Broccoli");
        map.put(19, "Vienna_sausage");
        map.put(20, "Pork_belly");
        map.put(21, "Shrimp");
        map.put(22, "Sausage");
        map.put(23, "Bean_sprouts");
        map.put(24, "Sundae");
        map.put(25, "Sliced_cheese");
        map.put(26, "Spinach");
        map.put(27, "Rice_grains");
        map.put(28, "Cooked_rice");
        map.put(29, "Zucchini");
        map.put(30, "Cabbage");
        map.put(31, "Onion");
        map.put(32, "Cucumber");
        map.put(33, "Squid");
        map.put(34, "Red_cabbage");
        map.put(35, "Canned_tuna");
        map.put(36, "Cheddar_cheese");
        map.put(37, "Cheese");
        map.put(38, "Soybean_sprouts");
        map.put(39, "Tomato");
        map.put(40, "Paprika");
        map.put(41, "Yellow_paprika");
        map.put(42, "Red_paprika");
        map.put(43, "Enoki_mushroom");
        map.put(44, "Ham");
        return map;
    }

    public List<String> ai_ex() {
        List<String> detectedClasses = new ArrayList<>();
        String pythonInterpreter = "C:\\Users\\gjw19\\AppData\\Local\\Programs\\Python\\Python312\\python.exe"; // Path to Python interpreter
        ProcessBuilder processBuilder = new ProcessBuilder(pythonInterpreter, pythonScriptPath, image);
        processBuilder.redirectErrorStream(true);
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);  // 출력된 내용을 콘솔에 표시하여 디버깅
                if (line.matches("\\d+")) {
                    int classId = Integer.parseInt(line.trim());
                    String className = classNames.getOrDefault(classId, "Unknown");
                    detectedClasses.add(className);
                } else {
                    System.err.println("Invalid class detected: " + line);
                }
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Python script exited with error code: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return detectedClasses;
    }
}
