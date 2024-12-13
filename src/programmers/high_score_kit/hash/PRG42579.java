package programmers.high_score_kit.hash;

import java.util.*;

public class PRG42579 {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> totalPlaysByGenre = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            totalPlaysByGenre.put(genres[i], totalPlaysByGenre.getOrDefault(genres[i], 0) + plays[i]);
        }

        List<String> genresList = new ArrayList<>(totalPlaysByGenre.keySet());
        genresList.sort((g1, g2) -> totalPlaysByGenre.get(g2) - totalPlaysByGenre.get(g1));

        Map<String, PriorityQueue<Song>> songsByGenre = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            songsByGenre.putIfAbsent(genres[i], new PriorityQueue<>());
            songsByGenre.get(genres[i]).offer(new Song(i, plays[i]));
        }

        List<Integer> indexes = new ArrayList<>();

        for (String genre : genresList) {
            PriorityQueue<Song> songs = songsByGenre.get(genre);
            int songCount = 2;

            while (!songs.isEmpty() && songCount > 0) {
                songCount--;
                indexes.add(songs.poll().index);
            }
        }

        return indexes
                .stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private class Song implements Comparable<Song> {
        int index;
        int play;

        public Song(int index, int play) {
            this.index = index;
            this.play = play;
        }

        @Override
        public int compareTo(Song s) {
            return s.play == this.play
                    ? this.index - s.index
                    : s.play - this.play;
        }
    }
}
