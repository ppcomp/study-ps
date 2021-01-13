# 2019 카카오 2차 코딩테스트 - 엘리베이터

---

## 개요

---

[https://github.com/kakao-recruit/2019-blind-2nd-elevator](https://github.com/kakao-recruit/2019-blind-2nd-elevator)

엘리베이터 동작 알고리즘을 효율적으로 짜는 것이 중요한 문제

## 엘리베이터 명령어 알고리즘 의사코드

---

```
for(엘리베이터 수만큼 반복) {
    엘리베이터의 현재 층 확인 
    if(타고 있는 사람이 없을 경우) {
        현재층에 탈 사람이 있으면 태움
        아니면 이동
    } 
    else if(탈 사람이 있거나 내릴 사람이 있으면) {
        내릴 사람 있으면 내림
				탈 사람 있으면 태움
    } 
    else {    //첫번째 탑승객의 목적지로 이동
        목적지에 도착했으면 내림
        아니면 이동
    }
    위에서 나온 결과를 토대로 엘리베이터 명령어 만들고 명령어 리스트에 추가
}
명령어 리스트 Json으로 변환 후 반환
```

## 해결 과정

---

알고리즘은 제문이의 아이디어를 참고했다.

- 엘리베이터의 이동은 탑승객의 탑승순서대로 이동(FIFO)
- 탑승객의 목적지로 이동하는 동안 목적지 방향과 같고 목적지 도착전에 내릴 수 있는 사람 있으면 태우고 가다가 내려줌

### Model

- Call - 데이터 클래스
- Command - 데이터 클래스
- Elevator - 데이터 클래스
- WaitFloor - 데이터 클래스
- JsonParse - 서버에서 받아온 Json 데이터를 자바 클래스에 맵핑해주는 클래스
- Simulation - 엘리베이터를 동작시키는 클래스

## 결과

---

![image](https://user-images.githubusercontent.com/47655983/104413600-eedd8780-55b1-11eb-9674-ef668883cb4c.png)
어피치 맨션

![image](https://user-images.githubusercontent.com/47655983/104413659-0e74b000-55b2-11eb-8a95-b3e8552f3908.png)
제이지 빌딩

![image](https://user-images.githubusercontent.com/47655983/104413833-690e0c00-55b2-11eb-997f-813c7424daf6.png)
라이언 타워