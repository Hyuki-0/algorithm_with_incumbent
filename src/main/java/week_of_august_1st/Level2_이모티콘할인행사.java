package week_of_august_1st;

import java.util.Arrays;

public class Level2_이모티콘할인행사 {

  /* 목표 : 1. 이모티콘 플러스 서비스 가입자를 최대한 늘리는 것.
   *       2. 이모티콘 판매액을 최대한 늘리는 것.
   *
   * 중점 : 할인은 이모티콘별로 전체가 동일하게 적용된다.
   *
   * 해결 : Math.pow(4, 7)의 경우의 수이므로, 완전탐색으로 수행 가능
   *     : pruning 방식은 전체에서 최소 구매 할인 비율을 찾아 해당 위치부터 탐색을 진행한다.
   * */

  static int[] salePercent = {10, 20, 30, 40};
  static int min = Integer.MAX_VALUE, joins = 0, moneys = 0;


  public static int[] solution(int[][] users, int[] emoticons) {
    // dfs 시작 범위
    int cur = 0;

    // 1. 최소 할인 비율을 구한다. => 범위 최소화
    for (int[] user : users) {
      min = Math.min(min, user[0]);
    }

    for (int i = 0; i < salePercent.length; i++) {
      if (min <= salePercent[i]) {
        min = i;
        break;
      }
    }

    dfs(users, emoticons, 0, new int[emoticons.length]);
    return new int[]{joins, moneys};
  }

  public static void dfs(int[][] users, int[] emoticons, int cur, int[] discounts) {

    if (cur == emoticons.length) {
      calculation(users, emoticons, discounts);
      return;
    }

    for (int i = cur; i < emoticons.length; i++) {
      // 이모티콘별 최적의 할인율을 찾는다.
      for (int m = min; m < salePercent.length; m++) {
        discounts[i] = salePercent[m];
        dfs(users, emoticons, i + 1, discounts);
      }
    }
  }

  public static void calculation(int[][] users, int[] emoticons, int[] discounts) {
    int join = 0, money = 0;

    for (int[] user : users) {
      int minPercent = user[0], maxPay = user[1], billed = 0;
      for (int i = 0; i < emoticons.length; i++) {
        if (minPercent > discounts[i]) {
          continue;
        }
        billed += emoticons[i] * (100 - discounts[i]) / 100;
      }

      if (maxPay <= billed) {
        join++;
      } else {
        money += billed;
      }
    }

    if (joins < join) {
      joins = join;
      moneys = money;
    } else if (joins == join && money > moneys) {
      moneys = money;
    }
  }

  public static void main(String[] args) {
    int[][] users = {{40, 10000}, {25, 10000}};
    int[] emoticons = {7000, 9000};

    System.out.println(Arrays.toString(solution(users, emoticons)));

    users = new int[][] {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
    emoticons = new int[] {1300, 1500, 1600, 4900};
    System.out.println(Arrays.toString(solution(users, emoticons)));
  }
}
