package week_of_august_1st;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Gold4_토마토 {

  static Queue<Tomato> queue = new LinkedList<>();
  static int maxDays = 0;
  static int[] dx = {1, 0, 0, -1, 0, 0};
  static int[] dy = {0, 1, 0, 0, -1, 0};
  static int[] dh = {0, 0, 1, 0, 0, -1};

  static class Tomato {

    int x;
    int y;
    int h;
    int days;

    public Tomato(int x, int y, int h, int days) {
      this.x = x;
      this.y = y;
      this.h = h;
      this.days = days;
    }
  }

  public static int solution(int cols, int rows, int heights, String[] boardMap) {
    int[][][] board = new int[heights][rows][cols];

    int idx = 0;
    for (int height = 0; height < heights; height++) {
      for (int row = 0; row < rows; row++) {
        int col = 0;
        for (String data : boardMap[idx++].split(" ")) {
          int val = Integer.parseInt(data);
          board[height][row][col] = val;

          if (val == 1) {
            queue.add(new Tomato(col, row, height, 0));
          }
          col++;
        }
      }
    }

    while (!queue.isEmpty()) {
      Tomato cur = queue.poll();

      for (int i = 0; i < 6; i++) {
        int moveX = cur.x + dx[i];
        int moveY = cur.y + dy[i];
        int moveH = cur.h + dh[i];

        if (moveX >= 0 && moveX < cols && moveY >= 0 && moveY < rows && moveH >= 0
            && moveH < heights) {
          if (board[moveH][moveY][moveX] != -1 && board[moveH][moveY][moveX] != 1) {
            board[moveH][moveY][moveX] = 1;
            int nDays = cur.days + 1;
            maxDays = Math.max(maxDays, nDays);
            queue.add(new Tomato(moveX, moveY, moveH, nDays));
          }
        }
      }

      Arrays.stream(board).forEach(i -> {
        Arrays.stream(i).forEach(z -> System.out.println(Arrays.toString(z)));
        System.out.println("=====층 분리=====");
      });
      System.out.println("==================================================");
    }

    if (isAllRiped(board)) {
      return maxDays;
    } else {
      return -1;
    }
  }

  public static boolean isAllRiped(int[][][] board) {
    for (int height = 0; height < board.length; height++) {
      for (int row = 0; row < board[height].length; row++) {
        for (int col = 0; col < board[height][row].length; col++) {
          if (board[height][row][col] == 0) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {

    String[] boardMap = {
        "0 0 0 0 0",
        "0 0 0 0 0",
        "0 0 0 0 0",
        "0 0 0 0 0",
        "0 0 1 0 0",
        "0 0 0 0 0"
    };
    System.out.println(solution(5, 3, 2, boardMap));

    boardMap = new String[]{
        "0 -1 0 0 0",
        "-1 -1 0 1 1",
        "0 0 0 1 1"
    };
//    System.out.println(solution(5, 3, 1, boardMap));

    boardMap = new String[] {
        "1 1 1 1",
        "1 1 1 1",
        "1 1 1 1",
        "1 1 1 1",
        "-1 -1 -1 -1",
        "1 1 1 -1"
    };
//    System.out.println(solution(4, 3, 2, boardMap));
  }
}
