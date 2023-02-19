package 내배코.w2;

import java.util.*;

public class W2_1 {
    public String[] solution(String[][] booked, String[][] unbooked) {
        Queue<Customer> bookedQueue = new LinkedList<>();
        for (String[] info : booked) bookedQueue.offer(new Customer(info[0], info[1]));

        Queue<Customer> unbookedQueue = new LinkedList<>();
        for (String[] info : unbooked) unbookedQueue.offer(new Customer(info[0], info[1]));

        List<String> answer = new ArrayList<>();

        int currentTime = Math.min(bookedQueue.peek().arrivalTime, unbookedQueue.peek().arrivalTime);

        while (!bookedQueue.isEmpty() || !unbookedQueue.isEmpty()) {
            if (bookedQueue.isEmpty()) {
                for (Customer c : unbookedQueue) answer.add(c.name);
                break;
            }
            if (unbookedQueue.isEmpty()) {
                for (Customer c : bookedQueue) answer.add(c.name);
                break;
            }

            Customer bc = bookedQueue.peek();
            Customer uc = unbookedQueue.peek();

            if (currentTime >= bc.arrivalTime) {
                answer.add(bookedQueue.poll().name);
                currentTime += 10;
            } else if (currentTime >= uc.arrivalTime) {
                answer.add(unbookedQueue.poll().name);
                currentTime += 10;
            } else {
                Customer c;
                if (bc.arrivalTime < uc.arrivalTime) c = bookedQueue.poll();
                else c = unbookedQueue.poll();
                answer.add(c.name);
                currentTime = c.arrivalTime + 10;
            }
        }

        return answer.toArray(String[]::new);
    }

    static class Customer {
        final int arrivalTime;
        final String name;

        Customer(String arrivalTime, String name) {
            this.arrivalTime = parseTime(arrivalTime);
            this.name = name;
        }

        public int parseTime(String time) {
            String[] split = time.split(":");
            return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
        }
    }

    /*
    2주차(중간고사) 1번 문제

    효율성 테스트는 없고 정확성 테스트만 12개가 있었음.
    모두 통과해서 정답 처리 되었으나 코드가 다소 길어진 측면이 있었고, 모범 답안을 보며 비교해봄. (solution2가 원래 내 풀이)

    1. Customer 객체 생성 및 활용
        고객 정보는 이름과 도착 시간 2가지인데 나는 이걸 String[] 그대로 사용했음.
        그러나 Customer 객체를 생성해서 활용하는게 훨씬 직관적이고 객체지향적임.

    2. parseTime()으로 시간 데이터 파싱
        주어진 시간 데이터는 "HH:mm" 형태로 되어있는데, 다른 사람들 풀이를 보면 SimpleDateFormat 등으로 파싱하는 경우도 있었음
        그에 비해 나는 그냥 문자열 그대로 사용해서 다소 번거롭게 시간 비교 메서드를 따로 만들어서 써야 했음.
        그런데 "시간 + 분" 데이터를 처음부터 분 데이터로 파싱해놓으면 비교 메서드가 따로 필요 없어서 편리함.

    3. while문 한개로 처리
        나는 while (!bookedQueue.isEmpty() && !unbookedQueue.isEmpty()) 1개를 돌리고,
        2개의 큐 중 데이터가 남은 쪽을 따로 다시 순회하면서 정답 리스트에 이름을 추가함. (즉 while문을 1 + 2개 만들어놓고 1 + 1개 사용)
        그런데 애초에 메인 while문의 조건식을 &&이 아니라 ||로 하면 1개로 끝낼 수 있음

    4. 손님이 들어갈 순서를 정할 때 분기 처리
        나는 아예 손님 1과 2, 그리고 currentTime을 파라미터로 받아 먼저 들어갈 사람의 이름을 반환하는 메서드를 정의해서 사용함
        해당 메서드 내에서 분기 처리를 if문 4개로 했는데, 잘 생각해보면 모범 답안처럼 if - else if - else 로 처리 가능함

    결국 내 풀이는 서브 메서드를 3개나 만들어서 사용하느라 코드가 길어진 단점이 있었음.
    - compareTime(): 시간 비교
    - processTime(): 시간 10분 뒤로 세팅
    - giveOrder(): 먼저 들어갈 고객 선정
    그런데 시간 데이터만 분 데이터로 파싱해서 쓰면 그 중 첫 2개는 필요가 없어지고, 분기 처리만 간소화해도 3번째 메서드도 필요 없어짐.
    그래도 의의를 둘 만한 점은 풀이 흐름 자체가 모범 답안과 동일했다는 것. 다음번에는 코드 간소화를 신경써가며 풀면 될 것 같음.
     */

    public String[] solution2(String[][] booked, String[][] unbooked) {
        List<String> answerList = new ArrayList<>(); // 이름을 편리하게 추가하기 위한 문자열 리스트

        // 예약자와 비예약자 각각 큐를 생성하고 원소를 넣음
        Queue<String[]> bookedQueue = new LinkedList<>();
        Queue<String[]> unbookedQueue = new LinkedList<>();

        for (String[] info : booked) {
            bookedQueue.offer(info);
        }

        for (String[] info : unbooked) {
            unbookedQueue.offer(info);
        }

        // 기준 시간 초기화 (예약과 무관하게 가장 빨리온 사람의 도착 시간으로 설정)
        String currentTime = compareTime(booked[0][0], unbooked[0][0]) ? booked[0][0] : unbooked[0][0];

        // 둘 중 어디라도 큐의 원소가 소진되면 루프 종료
        while (!bookedQueue.isEmpty() && !unbookedQueue.isEmpty()) {
            // 예약자큐와 비예약자큐에서 각각 한명씩 뽑아 대조
            String[] personA = bookedQueue.peek();
            String[] personB = unbookedQueue.peek();
            String name = giveOrder(personA, personB, currentTime); // 먼저 들어갈 사람을 정하고 이름 추출

            // 먼저 들어갈 사람이 예약자라면
            if (name.equals(personA[1])) {
                bookedQueue.poll(); // 손님을 예약자큐에서 제거
                answerList.add(name); // 손님의 이름을 정답 리스트에 추가

                if (compareTime(currentTime, personA[0])) { // 먼저 들어갈 손님이 아직 도착하지 않았다면
                    currentTime = processTime(personA[0]); // 기준 시간을 (해당 손님의 도착 시간 + 10분 뒤)로 변경
                    continue;
                }
                currentTime = processTime(currentTime); // 기준 시간을 (현재 시간 + 10분 뒤)로 변경
            } else {
                unbookedQueue.poll();
                answerList.add(name);

                if (compareTime(currentTime, personB[0])) {
                    currentTime = processTime(personB[0]);
                    continue;
                }
                currentTime = processTime(currentTime);
            }
        }

        // 원소가 남은 큐가 예약자큐라면 남은 사람들 전부 순서대로 정답 리스트에 이름 추가
        while (!bookedQueue.isEmpty()) {
            answerList.add(bookedQueue.poll()[1]);
        }

        // 원소가 남은 큐가 비예약자큐라면 남은 사람들 전부 순서대로 정답 리스트에 이름 추가
        while (!unbookedQueue.isEmpty()) {
            answerList.add(unbookedQueue.poll()[1]);
        }

        return answerList.toArray(new String[0]);
    }

    // 시간과 예약 유무를 고려했을 때 누가 먼저 들어갈지 결정해서 이름을 반환
    public String giveOrder(String[] personA, String[] personB, String currentTime) {
        String timeA = personA[0];
        String timeB = personB[0];

        if (compareTime(timeA, currentTime) && compareTime(timeB, currentTime)) {
            return personA[1];
        }

        if (compareTime(timeA, currentTime) && !compareTime(timeB, currentTime)) {
            return personA[1];
        }

        if (!compareTime(timeA, currentTime) && compareTime(timeB, currentTime)) {
            return personB[1];
        }

        if (!compareTime(timeA, currentTime) && !compareTime(timeB, currentTime)) {
            return compareTime(timeA, timeB) ? personA[1] : personB[1];
        }

        return personA[1];
    }

    // 시간을 10분 뒤로 세팅
    public String processTime(String t) {
        String[] time = t.split(":");
        int hour = Integer.valueOf(time[0]);
        int minute = Integer.valueOf(time[1]);

        if (minute + 10 >= 60) {
            hour++;
            minute = (minute + 10) - 60;
        } else {
            minute += 10;
        }

        return "" + hour + ":" + minute;
    }

    // 주어진 두 시간 중, t1이 더 이른 시간인 경우 true, 그렇지 않으면 false 반환
    public boolean compareTime(String t1, String t2) {
        String[] time1 = t1.split(":");
        String[] time2 = t2.split(":");

        int h1 = Integer.valueOf(time1[0]);
        int m1 = Integer.valueOf(time1[1]);

        int h2 = Integer.valueOf(time2[0]);
        int m2 = Integer.valueOf(time2[1]);

        if (h1 < h2) {
            return true;
        } else if (h1 == h2) {
            if (m1 <= m2) return true;
            else return false;
        } else {
            return false;
        }
    }
}

