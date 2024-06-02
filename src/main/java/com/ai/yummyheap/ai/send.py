from ultralytics import YOLO
import sys

def detect_objects(image_path):
    # 학습된 모델 경로를 지정하여 모델 로드
    model = YOLO('src/main/java/com/ai/yummyheap/ai/best.pt')

    # 이미지에서 객체 탐지 수행
    results = model(image_path)

    detected_classes = set()

    # 탐지된 객체의 클래스 수집
    for result in results:
        for box in result.boxes:
            cls = int(box.cls)  # 클래스
            detected_classes.add(cls)

    # 클래스 목록을 출력
    for cls in detected_classes:
        print(cls)

if __name__ == "__main__":
    image_path = sys.argv[1]
    detect_objects(image_path)