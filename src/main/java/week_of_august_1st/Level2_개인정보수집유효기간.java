package week_of_august_1st;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Level2_개인정보수집유효기간 {

  /*
   * 1. 문제 요약 : 모든 달은 28일까지만 존재 (즉, 1달 28일) -> 28 * 12 = 336
   * -> 약정이 지나 파기해야할 대상 찾기
   * -> 반드시 한 개 이상의 파기 계약이 존재한다.
   * -> 당일까지도 포함한다.
   *
   * 2. 해결 : 단순 Greedy 구현 
   * */

  static List<Integer> terminate_contract = new ArrayList<>();
  static Map<String, Integer> contracts = new HashMap<>();

  public static int[] solution(String today, String[] terms, String[] privacies) {
    for (String term : terms) {
      String[] proceed = term.split(" ");
      contracts.put(proceed[0], Integer.parseInt(proceed[1]));
    }

    int todayToDay = toDay(today.split("\\."), "");

    for (int i = 0; i < privacies.length; i++) {
      String[] privacy = privacies[i].split(" ");
      int expiredDate = toDay(privacy[0].split("\\."), privacy[1]);

      if(expiredDate <= todayToDay) {
        terminate_contract.add(i+1);
      }
    }

    return terminate_contract.stream().mapToInt(Integer::intValue).toArray();
  }

  public static int toDay(String[] date, String contract) {
    int today =  (12 * 28 * Integer.parseInt(date[0])) + (28 * Integer.parseInt(date[1])) + Integer.parseInt(date[2]);

    if(!contract.isBlank()) {
      today += 28 * contracts.get(contract);
    }

    return today;
  }

  public static void main(String[] args) {
    String today = "2022.05.19";
    String[] terms = {"A 6", "B 12", "C 3"};
    String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};

    System.out.println(Arrays.toString(solution(today, terms, privacies)));
  }
}
