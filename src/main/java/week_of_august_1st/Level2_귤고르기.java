package week_of_august_1st;

import java.util.*;

public class Level2_귤고르기 {

    static Map<Integer, Integer> mikangs = new HashMap<>();

    public static int solution(int k, int[] tangerine) {
        int type_cnt = 0;
        for(int mikang : tangerine) {
            mikangs.put(mikang, mikangs.getOrDefault(mikang, 0) + 1);
        }

        List<Integer> keySet = new ArrayList<>(mikangs.keySet());

        Collections.sort(keySet, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return mikangs.get(o2).compareTo(mikangs.get(o1));
            }
        });

        for(Integer key : keySet) {
            k -= mikangs.get(key);
            type_cnt++;

            if(k <= 0) {
                break;
            }
        }

        return type_cnt;
    }

    public static void main(String[] args) {
        int k = 6;
        int[] tangerine = new int[]{1, 3, 2, 5, 4, 5, 2, 3};
        System.out.println(solution(k, tangerine));
    }
}
