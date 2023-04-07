package programmers;

import java.time.*;

public class PRG17683 {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int longestDuration = 0;
        String melody = parseSharp(m);

        for (String musicInfo : musicinfos) {
            String[] data = musicInfo.split(",");

            int duration = getDuration(data[0], data[1]);
            String score = parseSharp(data[3]);
            String playedMelody = getPlayedMelody(score, duration);

            if (playedMelody.contains(melody) && duration > longestDuration) {
                answer = data[2];
                longestDuration = duration;
            }
        }

        return answer;
    }

    public String parseSharp(String score) {
        return score
                .replaceAll("C#", "c")
                .replaceAll("D#", "d")
                .replaceAll("F#", "f")
                .replaceAll("G#", "g")
                .replaceAll("A#", "a");
    }

    public int getDuration(String t1, String t2) {
        LocalTime lt1 = LocalTime.parse(t1);
        LocalTime lt2 = LocalTime.parse(t2);
        Duration duration = Duration.between(lt1, lt2);

        return (int) duration.toMinutes();
    }

    public String getPlayedMelody(String score, int duration) {
        if (score.length() > duration) {
            return score.substring(0, duration);
        } else if (score.length() == duration) {
            return score;
        } else {
            int repetition = duration / score.length();
            int remainingTime = duration - score.length() * repetition;
            return score.repeat(repetition) + score.substring(0, remainingTime);
        }
    }
}
