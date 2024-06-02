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
        map.put(0, "가지");
        map.put(1, "개맛살");
        map.put(2, "고구마");
        map.put(3, "고등어");
        map.put(4, "김");
        map.put(5, "노란파프리카");
        map.put(6, "피망");
        map.put(7, "달걀");
        map.put(8, "닭가슴살");
        map.put(9, "당근");
        map.put(10, "대파");
        map.put(11, "두부");
        map.put(12, "마늘");
        map.put(13, "쌀");
        map.put(14, "배추");
        map.put(15, "김치");
        map.put(16, "버섯");
        map.put(17, "베이컨");
        map.put(18, "브로콜리");
        map.put(19, "비엔나소시지");
        map.put(20, "삼겹살");
        map.put(21, "새우");
        map.put(22, "소시지");
        map.put(23, "숙주나물");
        map.put(24, "순대");
        map.put(25, "슬라이스치즈");
        map.put(26, "시금치");
        map.put(27, "쌀");
        map.put(28, "밥");
        map.put(29, "애호박");
        map.put(30, "양배추");
        map.put(31, "양파");
        map.put(32, "오이");
        map.put(33, "오징어");
        map.put(34, "적양배추");
        map.put(35, "참치캔");
        map.put(36, "체다치즈");
        map.put(37, "치즈");
        map.put(38, "콩나물");
        map.put(39, "토마토");
        map.put(40, "파프리카");
        map.put(41, "노란파프리카");
        map.put(42, "빨간파프리카");
        map.put(43, "팽이버섯");
        map.put(44, "햄");
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
                if (line.matches("\\d+")) {
                    int classId = Integer.parseInt(line.trim());
                    String className = classNames.getOrDefault(classId, "알 수 없음");
                    detectedClasses.add(className);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return detectedClasses;
    }
}