package week_of_july_4th;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Level2_호텔대실 {

  public static int solution(String[][] book_time) {
    int answer = 0;

    List<Team> bookingList = Arrays.stream(book_time)
        .map(times -> {
          LocalTime start = LocalTime.parse(times[0]);
          LocalTime end = LocalTime.parse(times[1]);

          LocalTime lastCheckInTime = LocalTime.parse("23:49");
          end = end.isBefore(lastCheckInTime) ? end.plusMinutes(10) : end;

          return new Team(start, end);
        }).sorted()
        .collect(Collectors.toList());

    System.out.println(bookingList);

    while (!bookingList.isEmpty()) {
      List<Team> notAssignedTeam = new ArrayList<>();

      LocalTime outTime = bookingList.getFirst().end;
      bookingList.removeFirst();

      for (Team team : bookingList) {
        if(outTime.isBefore(team.start) || outTime.equals(team.start)) {
          outTime = team.end;
        } else {
          notAssignedTeam.add(team);
        }
      }

      Collections.sort(notAssignedTeam);
      bookingList = notAssignedTeam;
      answer++;
    }

    return answer;
  }

  public static void main(String[] args) {
    String[][] book_time = {{"01:00", "23:59"}, {"09:00", "10:00"}};

    System.out.println(solution(book_time));
  }

  static class Team implements Comparable<Team> {

    LocalTime start;
    LocalTime end;

    public Team(LocalTime start, LocalTime end) {
      this.start = start;
      this.end = end;
    }

    @Override
    public int compareTo(Team o) {
      return this.start.compareTo(o.start);
    }

    @Override
    public String toString() {
      return "Team{" +
          "start=" + start +
          ", end=" + end +
          '}';
    }
  }
}
