package week_of_august_1st;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Silver2_트리의부모찾기 {

  // 연결된 노드 중 특정 노드를 부모로 만들어 부모 자식 관계를 나타낸다.

  static Queue<Integer> queue = new LinkedList<>();
  static boolean[] visited;
  static int[] parents;

  public static void solution(int N, int[][] in) {
    List<Integer>[] links = new List[N + 1];
    visited = new boolean[N + 1];
    parents = new int[N + 1];

    for (int i = 1; i <= N; i++) {
      links[i] = new ArrayList<>();
    }

    for (int[] edge : in) {
      int a = edge[0], b = edge[1];

      links[a].add(b);
      links[b].add(a);
    }

    queue.add(1);
    visited[1] = true;
    while (!queue.isEmpty()) {
      int cur = queue.poll();

      List<Integer> children = links[cur];
      Collections.sort(children);
      for (int child : children) {
        if (!visited[child]) {
          visited[child] = true;
          parents[child] = cur;
          queue.add(child);
        }
      }
    }

    System.out.println(Arrays.toString(parents));
  }

  public static void main(String[] args) {
    int[][] in = new int[][]{{1, 6}, {6, 3}, {3, 5}, {4, 1}, {2, 4}, {4, 7}};
    solution(7, in);
  }
}
