package week_of_august_1st;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Level2_오픈채팅방 {

    private static final String IN_SUFFIX = "님이 들어왔습니다.";
    private static final String LEFT_SUFFIX = "님이 나갔습니다.";

    static Map<String, String> inUsers = new HashMap<>();

    public static String[] solution(String[] record){
        int leaveCnt = 0;

        for (String s : record) {
            String[] payload = s.split(" ");

            switch (payload[0]) {
                case "Enter":
                    inUsers.put(payload[1], payload[2]);
                    break;
                case "Change":
                    inUsers.put(payload[1], payload[2]);
                    leaveCnt++;
                    break;
            }
        }

        String[] answer = new String[record.length - leaveCnt];
        int idx = 0;
        for (String s : record) {
            String[] payload = s.split(" ");

            switch (payload[0]) {
                case "Enter":
                    answer[idx++] = inUsers.get(payload[1]) + IN_SUFFIX;
                    break;
                case "Leave":
                    answer[idx++] = "\"" + inUsers.get(payload[1]) + LEFT_SUFFIX;
                    break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] record = new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        System.out.println(Arrays.toString(solution(record)));
    }
}
