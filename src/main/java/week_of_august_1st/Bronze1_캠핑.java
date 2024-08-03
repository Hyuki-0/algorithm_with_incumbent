package week_of_august_1st;

public class Bronze1_캠핑 {

  public static int solution(int p, int l, int v) {
    return ((v / l) * p) + (v % l);
  }

  public static void main(String[] args) {

    System.out.println(solution(5, 8, 20));
    System.out.println(solution(5, 8, 17));
  }
}
