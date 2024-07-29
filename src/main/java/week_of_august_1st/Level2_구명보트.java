package week_of_august_1st;

import java.util.Arrays;

public class Level2_구명보트 {

    /*
     * 배 최대 승선 인원은 두명
     *
     * */

    public static int solution(int[] people, int limit) {
        Arrays.sort(people);

        int i = 0, j = people.length - 1;

        for (; i < j; j--) {
            if (people[i] + people[j] <= limit)
                ++i;
        }
        return people.length - i;
    }

    public static void main(String[] args) {
        int[] people = new int[]{20, 60, 70, 80, 30};
        int limit = 100;
        System.out.println(solution(people, limit));
    }
}
