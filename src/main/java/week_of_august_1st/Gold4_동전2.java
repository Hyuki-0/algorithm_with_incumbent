package week_of_august_1st;

import java.util.Arrays;

public class Gold4_동전2 {

  static int[] board;

  public static int solution(int target, int[] coins) {
    board = new int[target + 1];
    Arrays.fill(board, 10001);
    board[0] = 0;

    for (int cnt = 0; cnt < coins.length; cnt++) {
      for (int start = coins[cnt]; start <= target; start++) {
        board[start] = Math.min(board[start], board[start - coins[cnt]] + 1);
      }
    }
    return board[target] != 10001 ? board[target] : -1;
  }

  public static void main(String[] args) {
    int[] coins = {2, 5, 13};

    System.out.println(solution(3, coins));
  }
}
