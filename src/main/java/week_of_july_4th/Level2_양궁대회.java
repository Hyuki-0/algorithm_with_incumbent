package week_of_july_4th;

import java.util.Arrays;

public class Level2_양궁대회 {

  // DFS 순회를 하며 라이언 스코어 보드 기록
  static int[] currentLionScoreBoard = new int[11];

  // 어피치를 이긴 경우 현재 max_value와 비교하여 currentLionScoreBoard 정보를 기록
  static int[] result = {-1};

  // lion이 play 하면서 어피치를 이긴 경우
  static int maxDiff = Integer.MIN_VALUE;

  static int times;

  /*
   * 1. 목표 : 10초 이내
   * 2. 유형 : DFS -> Back Tracking
   * 3. 해결 : 점수 획득 방식이 라이언 <= 어피치 화살 (점수 획득 실패) 라이언 > 어피치 화살 (점수 획득)
   * 즉, 특정 점수에서 한발만 더 맞추면 된다. -> 전체 탐색하면서 currentLionScoreBoard를 만든다.
   *
   * 4. 실수 : Worst Case 경우 분기 실수로 인한 n*n*n 현상 만듦
   */
  public static void solution(int n, int[] apeachScoreBoard) {
    Long start = System.currentTimeMillis();
    playLion(n, apeachScoreBoard, 0);
    Long end = System.currentTimeMillis();

    if (maxDiff < 0) {
      System.out.println(-1);
    } else {
      System.out.println(Arrays.toString(result));
      System.out.println(times);
      System.out.println("수행 시간 : " + (end - start) + "ms");
    }
  }


  public static void playLion(int n, int[] apeachScoreBoard, int tries) {

    if (tries == n) {
      times++;
      int lionScore = 0, apeachScore = 0;

      for (int i = 0; i < 10; i++) {
        if (currentLionScoreBoard[i] != 0 || apeachScoreBoard[i] != 0) {
          if (currentLionScoreBoard[i] <= apeachScoreBoard[i]) {
            apeachScore += (10 - i);
          } else {
            lionScore += (10 - i);
          }
        }
      }

      if (apeachScore < lionScore) {
        int diff = (lionScore - apeachScore);
        if (maxDiff <= diff) {
          result = currentLionScoreBoard.clone();
          maxDiff = diff;
        }
      }

      return;
    }

    for (int j = 0; j <= 10; j++) {
      // 한발만 더크면 되기 때문에 분기
      if(apeachScoreBoard[j] < currentLionScoreBoard[j]) break;
      currentLionScoreBoard[j]++;
      playLion(n, apeachScoreBoard, tries + 1);
      currentLionScoreBoard[j]--;
    }
  }

  public static void main(String[] args) {
    int n = 10;
    int[] info = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3};

    // case 1
    solution(n, info);
  }
}
